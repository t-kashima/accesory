package com.unuuu.android.accessory

import android.content.Context
import android.graphics.PixelFormat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView

public class AccesoryLifecycleExecutor : MyActivityLifecycleCallbacks.LifecycleExecutor, View.OnTouchListener {

    val view : View
    val imageView : ImageView
    val windowManager : WindowManager
    var oldx = 0
    var oldy = 0
    var clickListener : () -> Unit = {}
    var isOnClick = false

    public constructor(context : Context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        view = LayoutInflater.from(context).inflate(R.layout.overlay, null)
        view.setOnTouchListener(this)
        imageView = view.findViewById(R.id.overlay_image) as ImageView
    }

    override fun onForeground() {
        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)
        windowManager.addView(view, params)
    }

    override fun onBackground() {
        windowManager.removeView(view);
    }

    public fun setOnClickListener(listener : () -> Unit) {
        clickListener = listener
    }

    override fun onTouch(view : View, event : MotionEvent) : Boolean {
        // drag the view
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> isOnClick = true
            MotionEvent.ACTION_MOVE -> {
                isOnClick = false
                val params = view.layoutParams as WindowManager.LayoutParams
                params.x = params.x + (x - oldx)
                params.y = params.y + (y - oldy)
                windowManager.updateViewLayout(view, params);
            }
            MotionEvent.ACTION_CANCEL -> isOnClick = false
            MotionEvent.ACTION_UP -> {
                if (isOnClick) {
                    clickListener()
                }
            }
        }

        oldx = x
        oldy = y

        return false
    }

    /**
     * show the view
     */
    public fun show() {
        view.visibility = View.VISIBLE
    }

    /**
     * dismiss the view
     */
    public fun dismiss() {
        view.visibility = View.GONE
    }

    public fun setImageResource(resourceId : Int) {
        imageView.setImageResource(resourceId)
    }
}