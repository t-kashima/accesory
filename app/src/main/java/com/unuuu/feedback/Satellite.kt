package com.unuuu.feedback

import android.app.Application

public class Satellite {

    private var executor : SatelliteLifecycleExecutor

    private constructor(application: Application) {
        var lifecycleCallbacks = MyActivityLifecycleCallbacks()
        executor = SatelliteLifecycleExecutor(application.applicationContext)
        lifecycleCallbacks.addLifecycleExecutor(executor)
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    companion object {
        private var satellite : Satellite? = null

        fun with(application: Application) : Satellite {
            if (satellite == null) {
                satellite = Satellite(application)
            }
            return satellite!!
        }
    }

    public fun show() {
        executor.show()
    }

    public fun dismiss() {
        executor.dismiss()
    }

    /**
     * This listener when you click the satellite.
     */
    public fun setOnClickListener(listener : () -> Unit) {
        executor.setOnClickListener(listener)
    }
}


