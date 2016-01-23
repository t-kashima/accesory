package com.unuuu.android.accesory.sample

import android.app.Application
import com.unuuu.android.accesory.Accesory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Accesory.init(this, R.mipmap.ic_launcher)
    }
}