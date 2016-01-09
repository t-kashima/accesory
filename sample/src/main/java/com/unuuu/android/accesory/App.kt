package com.unuuu.android.accesory

import android.app.Application
import com.unuuu.andorid.accesory.R
import com.unuuu.android.accessory.Accesory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Accesory.with(this).setImageResource(R.drawable.suntv)
    }
}


