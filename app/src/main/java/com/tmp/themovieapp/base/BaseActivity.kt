package com.tmp.themovieapp.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes private val layoutId: Int): AppCompatActivity() {

    protected lateinit var binding:T

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, layoutId)

        initView()
        initViewModel()
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected open fun initView() {}
    protected open fun initViewModel() {}
    protected open fun initListener() {}
}