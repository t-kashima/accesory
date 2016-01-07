package com.unuuu.feedback

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

public class SatelliteLifecycleExecutor : MyActivityLifecycleCallbacks.LifecycleExecutor {

    var view: View? = null
    var windowManager: WindowManager? = null

    public constructor(context : Context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        view = LayoutInflater.from(context).inflate(R.layout.overlay, null)
        view?.setOnTouchListener(View.OnTouchListener { view, event ->
            val rect = Rect()
            view?.getHitRect(rect);
            return@OnTouchListener !rect.contains(event.x.toInt(), event.y.toInt())
        })
    }

    override fun onForeground() {
        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT)
        windowManager?.addView(view, params)
    }

    override fun onBackground() {
        windowManager?.removeView(view);
    }
}