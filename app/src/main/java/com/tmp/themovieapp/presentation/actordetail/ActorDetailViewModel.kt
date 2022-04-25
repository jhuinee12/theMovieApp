package com.tmp.themovieapp.presentation.actordetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.*
import com.tmp.themovieapp.repositories.ActorDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorDetailViewModel(private val actorDetailRepository: ActorDetailRepository) : BaseViewModel() {

    val isFavorite = MutableLiveData<Int>()

    fun addFavoriteMovie(actor: ActorInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            actorDetailRepository.insert(actor)
            countMovieWhereId(actor.id)
        }
    }

    fun countMovieWhereId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isFavorite.postValue(actorDetailRepository.getCountId(id))
        }
    }

    fun deleteFavoriteMovie(actor: ActorInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            actorDetailRepository.delete(actor)
            countMovieWhereId(actor.id)
        }
    }
}