package com.unuuu.feedback.services;

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.graphics.Rect
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.unuuu.feedback.R

class FeedbackService: Service() {

    var mView: View? = null
    var mWindowManager: WindowManager? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(FeedbackService::class.java.simpleName, "onCreate");
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(FeedbackService::class.java.simpleName, "onStartCommand")

        val layoutInflater = LayoutInflater.from(this);
        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT)

        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mView = layoutInflater.inflate(R.layout.overlay, null)
        mWindowManager?.addView(mView, params)
        mView?.setOnTouchListener(View.OnTouchListener { view, event ->
            val rect = Rect()
            mView?.getHitRect(rect);
            if (!rect.contains(event.x.toInt(), event.y.toInt())) {
                return@OnTouchListener false
            }
            return@OnTouchListener true
        })
        return START_STICKY;
    }

    override fun onDestroy() {
        Log.d(FeedbackService::class.java.simpleName, "onDestroy");
        super.onDestroy();
        mWindowManager?.removeView(mView);
    }

    override fun onBind(intent: Intent) : IBinder? {
        Log.d(FeedbackService::class.java.simpleName, "onBind");
        return null;
    }
}
