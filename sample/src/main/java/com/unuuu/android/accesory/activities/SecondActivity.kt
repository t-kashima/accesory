package com.unuuu.android.accesory.activities

import android.os.Bundle
import com.unuuu.andorid.accesory.R
import com.unuuu.android.accessory.Accesory

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Accesory.with(application).dismiss()
    }
}



