package com.unuuu.feedback

import android.app.Application

public class Satellite {

    private constructor(application: Application) {
        var lifecycleCallbacks = MyActivityLifecycleCallbacks()
        lifecycleCallbacks.addLifecycleExecutor(SatelliteLifecycleExecutor(application))
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    companion object {
        private var satellite : Satellite? = null

        fun init(application: Application) {
            satellite = Satellite(application)
        }
    }
}


