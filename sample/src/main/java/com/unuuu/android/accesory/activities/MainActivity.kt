package com.unuuu.android.accesory.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.unuuu.andorid.accesory.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView : TextView = findViewById(R.id.activity_main_text) as TextView
        textView.setOnClickListener({ startActivity(Intent(this, SecondActivity::class.java)) })
    }
}
