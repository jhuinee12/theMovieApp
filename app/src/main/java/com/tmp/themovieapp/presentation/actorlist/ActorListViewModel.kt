package com.tmp.themovieapp.presentation.actorlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.ActorInfo
import com.tmp.themovieapp.entity.PopularActorList
import com.tmp.themovieapp.repositories.ActorListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActorListViewModel(private val actorListRepository: ActorListRepository) : BaseViewModel() {

    private val _actorList = MutableLiveData<MutableList<ActorInfo>>()
    val tempList = mutableListOf<ActorInfo>()
    val actorList = _actorList

    var page: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getPopularActors() {
        viewModelScope.launch {
            actorListRepository.getPopularActors(page.value!!)
                .enqueue(object : Callback<PopularActorList> {
                    override fun onResponse(
                        call: Call<PopularActorList>,
                        response: Response<PopularActorList>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                Log.d("Repository", "Actors: ${responseBody.actors}")
                                tempList.addAll(responseBody.actors)
                                _actorList.postValue(tempList.toMutableList())
                            } else {
                                Log.d("Repository", "Failed to get response")
                            }
                        }
                    }

                    override fun onFailure(call: Call<PopularActorList>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }

    fun getMyFavoriteActors() {
        viewModelScope.launch(Dispatchers.IO) {
            _actorList.postValue(actorListRepository.getAll())
        }
    }
}