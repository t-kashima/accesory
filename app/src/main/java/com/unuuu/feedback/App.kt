package com.unuuu.feedback

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Satellite.init(this)
    }
}

