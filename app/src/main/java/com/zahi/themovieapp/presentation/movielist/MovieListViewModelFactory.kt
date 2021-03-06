package com.zahi.themovieapp.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zahi.themovieapp.repositories.MovieListRepository

class MovieListViewModelFactory(private val movieListRepository: MovieListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieListRepository::class.java).newInstance(movieListRepository)
    }
}