package com.unuuu.android.accessory

import android.app.Application

public class Accesory {

    private var executor : AccesoryLifecycleExecutor

    private constructor(application: Application) {
        var lifecycleCallbacks = MyActivityLifecycleCallbacks()
        executor = AccesoryLifecycleExecutor(application.applicationContext)
        lifecycleCallbacks.addLifecycleExecutor(executor)
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    companion object {
        private var accesory : Accesory? = null

        fun with(application: Application) : Accesory {
            // singleton pattern
            if (accesory == null) {
                accesory = Accesory(application)
            }
            return accesory!!
        }
    }

    public fun show() {
        executor.show()
    }

    public fun dismiss() {
        executor.dismiss()
    }

    public fun setImageResource(resourceId : Int) {
        executor.setImageResource(resourceId)
    }

    /**
     * This listener when you click the satellite.
     */
    public fun setOnClickListener(listener : () -> Unit) {
        executor.setOnClickListener(listener)
    }
}


