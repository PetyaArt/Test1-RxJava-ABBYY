// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.example.test1.ui.presenter

import com.example.test1.di.RxBroadcastReceiver
import com.example.test1.ui.view.BatteryView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class PresenterBattery(private val view: BatteryView, private val rxBroadcastReceiver: RxBroadcastReceiver) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun onCreate() {
        disposable.add(Observable.create(rxBroadcastReceiver)
                .subscribe {viewState ->
                    view.render(viewState)
                })
    }

    fun onDestroy() = disposable.clear()

}