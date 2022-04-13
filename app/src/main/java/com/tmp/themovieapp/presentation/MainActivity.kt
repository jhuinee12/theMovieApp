package com.tmp.themovieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}