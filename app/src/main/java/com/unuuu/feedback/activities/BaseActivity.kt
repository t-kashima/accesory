package com.unuuu.feedback.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.unuuu.feedback.Satellite

abstract class BaseActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()

        Satellite.with(application).setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
        Satellite.with(application).show()
    }
}


