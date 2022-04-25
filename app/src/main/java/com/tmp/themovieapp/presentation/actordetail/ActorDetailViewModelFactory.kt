package com.tmp.themovieapp.presentation.actordetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tmp.themovieapp.repositories.ActorDetailRepository

class ActorDetailViewModelFactory(private val actorDetailRepository: ActorDetailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActorDetailRepository::class.java).newInstance(actorDetailRepository)
    }
}