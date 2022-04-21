package com.tmp.themovieapp.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.entity.PopularMovieList
import com.tmp.themovieapp.repositories.MovieListRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : BaseViewModel() {

    var bottomNavItem: MutableLiveData<Int> = MutableLiveData<Int>()
}