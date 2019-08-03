package com.example.test1.di

import android.content.Context
import com.example.test1.ui.presenter.PresenterBattery
import com.example.test1.ui.view.BatteryView

class DependencyInjection(private val applicationContext: Context) {

    fun getPresenterBattery(view: BatteryView) : PresenterBattery {
        return PresenterBattery(view, RxBroadcastReceiver(applicationContext))
    }
}