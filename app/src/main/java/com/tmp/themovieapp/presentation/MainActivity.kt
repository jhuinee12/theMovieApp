package com.tmp.themovieapp.presentation

import android.util.Log
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        super.initView()

        Log.d("TAG", "initView: 이곳은 Main")

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