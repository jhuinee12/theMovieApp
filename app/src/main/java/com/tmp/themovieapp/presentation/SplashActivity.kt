package com.tmp.themovieapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivitySplashBinding

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

    override fun initListener() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}