package com.example.test1.di

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.example.test1.ui.presenter.BatteryViewState
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

class RxBroadcastReceiver(val applicationContext: Context) : ObservableOnSubscribe<BatteryViewState> {

    override fun subscribe(emitter: ObservableEmitter<BatteryViewState>) {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (emitter.isDisposed) {
                    applicationContext.unregisterReceiver(this)
                    return
                }
                if (intent?.action != null && intent.action == Intent.ACTION_BATTERY_CHANGED) {
                    val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                    val isCharging =
                            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
                    emitter.onNext(
                            BatteryViewState(
                                    intent.getIntExtra(
                                            BatteryManager.EXTRA_LEVEL,
                                            0
                                    ), isCharging
                            )
                    )
                }
            }
        }
        applicationContext.registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}