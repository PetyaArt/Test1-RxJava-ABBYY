package com.example.test1.ui.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.App
import com.example.test1.R
import com.example.test1.ui.presenter.BatteryViewState
import kotlinx.android.synthetic.main.activity_view_battery.*

class ViewBatteryActivity : AppCompatActivity(), BatteryView {

    private val presenter by lazy {
        App.getDependencyInjection(this).getPresenterBattery(this)
    }
    private lateinit var anim: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_battery)

        anim = battery_charging.background as AnimationDrawable
        presenter.onCreate()
    }

    override fun render(batteryViewState: BatteryViewState) {
        charging(batteryViewState)
        levelBattery(batteryViewState)
    }

    private fun levelBattery(batteryViewState: BatteryViewState) {
        when (batteryViewState.batteryPercent) {
            in 0..20 -> battery_level.setImageResource(R.drawable.ic_battery_20)
            in 20..30 -> battery_level.setImageResource(R.drawable.ic_battery_30)
            in 30..50 -> battery_level.setImageResource(R.drawable.ic_battery_50)
            in 50..60 -> battery_level.setImageResource(R.drawable.ic_battery_60)
            in 60..80 -> battery_level.setImageResource(R.drawable.ic_battery_80)
            in 80..90 -> battery_level.setImageResource(R.drawable.ic_battery_90)
            in 90..100 -> battery_level.setImageResource(R.drawable.ic_battery_100)
        }
        battery_level_text_view.text = "${batteryViewState.batteryPercent} %"
    }

    private fun charging(batteryViewState: BatteryViewState) {
        if (batteryViewState.batteryCharging) {
            anim.start()
            battery_charging.visibility = View.VISIBLE
            battery_level.visibility = View.INVISIBLE
        } else {
            anim.stop()
            battery_charging.visibility = View.INVISIBLE
            battery_level.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
