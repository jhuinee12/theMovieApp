package com.tmp.themovieapp.presentation

import android.os.Bundle
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()
        binding.apply {  }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}