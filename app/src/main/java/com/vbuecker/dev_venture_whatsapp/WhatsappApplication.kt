package com.vbuecker.dev_venture_whatsapp

import android.app.Application
import com.vbuecker.dev_venture_whatsapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WhatsappApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WhatsappApplication)
            modules(modules = AppModule.module)
        }
    }
}