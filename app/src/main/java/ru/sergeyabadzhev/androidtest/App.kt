package ru.sergeyabadzhev.androidtest

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import ru.sergeyabadzhev.androidtest.di.mainModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                mainModule
            )
            androidContext(this@App)
        }
    }
}