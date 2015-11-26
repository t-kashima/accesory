package com.unuuu.feedback.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.unuuu.feedback.services.FeedbackService

abstract class BaseActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        startService(Intent(this, FeedbackService::class.java))
    }

    override fun onPause() {
        super.onPause()
        stopService(Intent(this, FeedbackService::class.java))
    }
}


