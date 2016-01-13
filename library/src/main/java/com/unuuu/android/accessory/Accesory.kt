package com.unuuu.android.accessory

import android.app.Application

public object Accesory {

    var executor : AccesoryLifecycleExecutor? = null

    public fun init(application : Application, resourceId : Int) {
        var lifecycleCallbacks = MyActivityLifecycleCallbacks()
        executor = AccesoryLifecycleExecutor(application.applicationContext)
        lifecycleCallbacks.addLifecycleExecutor(executor!!)
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
        executor?.setImageResource(resourceId)
    }

    public fun show() {
        executor?.show()
    }

    public fun dismiss() {
        executor?.dismiss()
    }

    /**
     * This listener when you click the satellite.
     */
    public fun setOnClickListener(listener : () -> Unit) {
        executor?.setOnClickListener(listener)
    }
}


