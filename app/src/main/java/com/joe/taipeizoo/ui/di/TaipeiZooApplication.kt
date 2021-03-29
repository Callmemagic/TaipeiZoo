package com.joe.taipeizoo.ui.di

import android.app.Application
import androidx.multidex.MultiDexApplication
import org.koin.core.context.startKoin

/**
 * author: Joe Cheng
 */
class TaipeiZooApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(appModule)) }
    }
}