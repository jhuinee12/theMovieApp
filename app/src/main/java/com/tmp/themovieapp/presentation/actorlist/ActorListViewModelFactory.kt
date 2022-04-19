package com.tmp.themovieapp.presentation.actorlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tmp.themovieapp.repositories.ActorListRepository
import com.tmp.themovieapp.repositories.MovieListRepository

class ActorListViewModelFactory(private val actorListRepository: ActorListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActorListRepository::class.java).newInstance(actorListRepository)
    }
}