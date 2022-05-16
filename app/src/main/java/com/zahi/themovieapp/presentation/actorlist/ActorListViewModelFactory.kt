package com.zahi.themovieapp.presentation.actorlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zahi.themovieapp.repositories.ActorListRepository

class ActorListViewModelFactory(private val actorListRepository: ActorListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ActorListRepository::class.java).newInstance(actorListRepository)
    }
}