package com.tmp.themovieapp.presentation.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tmp.themovieapp.repositories.MovieListRepository

class MovieDetailViewModelFactory(private val movieListRepository: MovieListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieListRepository::class.java).newInstance(movieListRepository)
    }
}