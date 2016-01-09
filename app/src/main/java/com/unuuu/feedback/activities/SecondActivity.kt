package com.unuuu.feedback.activities

import android.os.Bundle
import com.unuuu.feedback.R
import com.unuuu.feedback.Satellite

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Satellite.with(application).dismiss()
    }
}



