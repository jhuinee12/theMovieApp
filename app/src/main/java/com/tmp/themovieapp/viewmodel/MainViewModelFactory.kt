package com.tmp.themovieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tmp.themovieapp.repositories.MovieListRepository

class MainViewModelFactory(private val movieListRepository: MovieListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieListRepository::class.java).newInstance(movieListRepository)
    }
}