package com.tmp.themovieapp.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.*
import com.tmp.themovieapp.repositories.MovieDetailRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : BaseViewModel() {

    private val _actorInfo = MutableLiveData<MutableList<ActorInfo>>()
    val actorInfo = _actorInfo

    fun getMovieDetailInfo(movie_info: Int) {
        viewModelScope.launch {
            movieDetailRepository.getRepositories(movie_info)
                .enqueue(object : Callback<MovieDetail> {
                    override fun onResponse(
                        call: Call<MovieDetail>,
                        response: Response<MovieDetail>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                Log.d("Repository-Movie Detail", "Actor: ${responseBody.cast}")
                                _actorInfo.postValue(responseBody.cast)
                            } else {
                                Log.d("Repository-Movie Detail", "Failed to get response")
                            }
                        }
                    }

                    override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }
}