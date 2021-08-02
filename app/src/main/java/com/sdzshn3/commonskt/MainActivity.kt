package com.sdzshn3.commonskt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sdzshn3.commonsktlibrary.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("This toast is shown using library")
    }
}