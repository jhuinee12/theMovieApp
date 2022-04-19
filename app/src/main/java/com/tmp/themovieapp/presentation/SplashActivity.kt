package com.tmp.themovieapp.presentation

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivitySplashBinding
import com.tmp.themovieapp.presentation.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initView() {
        binding.apply { }

        Log.d("TAG", "initView: 이곳은 Splash")

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 500)
    }

    override fun initViewModel() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}