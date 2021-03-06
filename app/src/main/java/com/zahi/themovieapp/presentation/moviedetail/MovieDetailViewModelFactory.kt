package com.zahi.themovieapp.presentation.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zahi.themovieapp.repositories.MovieDetailRepository

class MovieDetailViewModelFactory(private val movieDetailRepository: MovieDetailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieDetailRepository::class.java).newInstance(movieDetailRepository)
    }
}