package com.unuuu.android.accessory

import android.app.Application

public object Accesory {

    var executor : AccesoryLifecycleExecutor? = null

    /**
     * initialize the accesory.
     */
    public fun init(application : Application, resourceId : Int) {
        val lifecycleCallbacks = MyActivityLifecycleCallbacks()
        executor = AccesoryLifecycleExecutor(application.applicationContext)
        lifecycleCallbacks.addLifecycleExecutor(executor!!)
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
        executor?.setImageResource(resourceId)
    }

    /**
     * Show the accesory.
     */
    public fun show() {
        executor?.show()
    }

    /**
     * Dismiss the accesory.
     */
    public fun dismiss() {
        executor?.dismiss()
    }

    /**
     * This listener is called when you click on the accesory.
     */
    public fun setOnClickListener(listener : () -> Unit) {
        executor?.setOnClickListener(listener)
    }
}


