package com.zahi.themovieapp.presentation.main

import androidx.lifecycle.MutableLiveData
import com.zahi.themovieapp.base.BaseViewModel

class MainViewModel() : BaseViewModel() {

    var bottomNavItem: MutableLiveData<Int> = MutableLiveData<Int>()
}