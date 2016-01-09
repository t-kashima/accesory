package com.unuuu.android.accesory.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.unuuu.android.accessory.Accesory

abstract class BaseActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()

        Accesory.with(application).setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
        Accesory.with(application).show()
    }
}


