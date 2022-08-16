package com.kkirrix.kkirikkiri.android

import android.app.Application
import com.kkirrix.kkirikkiri.di.dataModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule)
        }
    }
}