package com.example.test1

import android.app.Application
import android.content.Context
import com.example.test1.di.DependencyInjection

class App : Application() {

    companion object {
        fun getDependencyInjection(context: Context): DependencyInjection {
            return (context.applicationContext as App).dependencyInjection
        }
    }

    private lateinit var dependencyInjection: DependencyInjection

    override fun onCreate() {
        super.onCreate()
        dependencyInjection = DependencyInjection(applicationContext)
    }

}