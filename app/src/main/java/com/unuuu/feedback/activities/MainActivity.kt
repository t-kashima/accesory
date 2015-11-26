package com.unuuu.feedback.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import butterknife.bindView
import com.unuuu.feedback.R

class MainActivity : BaseActivity() {

    val mTextView: TextView by bindView(R.id.activity_main_frame_001)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView.setOnClickListener({ startActivity(Intent(this, SecondActivity::class.java)) })
    }
}
