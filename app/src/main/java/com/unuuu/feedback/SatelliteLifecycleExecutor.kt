package com.unuuu.feedback

import android.content.Context
import android.graphics.PixelFormat
import android.view.*
import android.widget.ImageView

public class SatelliteLifecycleExecutor : MyActivityLifecycleCallbacks.LifecycleExecutor {

    val view : View
    val imageView : ImageView
    val windowManager : WindowManager

    public constructor(context : Context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        view = LayoutInflater.from(context).inflate(R.layout.overlay, null)
        imageView = view.findViewById(R.id.overlay_image) as ImageView
    }

    override fun onForeground() {
        val windowLayoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)
        windowLayoutParams.gravity = Gravity.TOP or Gravity.LEFT;
        windowManager.addView(view, windowLayoutParams)

        view.setOnTouchListener(View.OnTouchListener { view, event ->
            if ((event.action == MotionEvent.ACTION_MOVE) or (event.action == MotionEvent.ACTION_DOWN)) {
                val xOffset = view.width / 2
                val yOffset = view.height
                val x = (event.rawX - xOffset).toInt()
                val y = (event.rawY - yOffset).toInt()
                val params = WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        x,
                        y,
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                                or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);
                params.gravity = Gravity.TOP or Gravity.LEFT;
                windowManager.updateViewLayout(view, params);
                return@OnTouchListener true;
            }
            return@OnTouchListener false
        })
    }

    override fun onBackground() {
        windowManager.removeView(view);
    }

    public fun setOnClickListener(listener : (v : View) -> Unit) {
    }
}