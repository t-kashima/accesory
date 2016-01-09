package com.unuuu.feedback

import android.app.Application
import android.view.View

public class Satellite {

    private var executor : SatelliteLifecycleExecutor

    private constructor(application: Application) {
        var lifecycleCallbacks = MyActivityLifecycleCallbacks()
        executor = SatelliteLifecycleExecutor(application)
        lifecycleCallbacks.addLifecycleExecutor(executor)
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    companion object {
        private var satellite : Satellite? = null

        fun init(application: Application) {
            satellite = Satellite(application)
        }
    }

    /**
     * This listener when you click the satellite.
     */
    public fun setOnClickListener(listener : (v : View) -> Unit) {
        executor.setOnClickListener(listener)
    }
}


