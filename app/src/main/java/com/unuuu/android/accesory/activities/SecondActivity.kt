package com.unuuu.android.accesory.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unuuu.andorid.accesory.R
import com.unuuu.android.accesory.Accesory

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Accesory.dismiss()
    }
}



