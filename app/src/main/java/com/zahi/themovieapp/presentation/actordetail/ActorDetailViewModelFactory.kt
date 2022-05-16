package com.zahi.themovieapp.presentation.actordetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zahi.themovieapp.repositories.ActorDetailRepository

class ActorDetailViewModelFactory(private val actorDetailRepository: ActorDetailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActorDetailRepository::class.java).newInstance(actorDetailRepository)
    }
}