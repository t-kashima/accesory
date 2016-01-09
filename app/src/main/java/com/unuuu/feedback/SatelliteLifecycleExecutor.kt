package com.unuuu.feedback

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.view.WindowManager
import android.widget.ImageView

public class SatelliteLifecycleExecutor : MyActivityLifecycleCallbacks.LifecycleExecutor {

    var imageView: ImageView
    var windowManager: WindowManager

    public constructor(context : Context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        updateImage(context, R.drawable.suntv)
    }

    /**
     * update the image using the drawable id.
     *
     * @param context context
     * @param drawableId drawable id
     */
    public fun updateImage(context : Context, drawableId : Int) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.resources, drawableId, options)
        options.inJustDecodeBounds = false;
        var scale : Int;
        if (options.outWidth < options.outHeight) {
            scale = options.outHeight / 40;
        } else {
            scale = options.outWidth / 40;
        }
        options.inSampleSize = scale;
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableId, options);
        imageView.setImageBitmap(bitmap)
    }

    override fun onForeground() {
        val windowLayoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT)
        windowManager.addView(imageView, windowLayoutParams)
    }

    override fun onBackground() {
        windowManager.removeView(imageView);
    }
}