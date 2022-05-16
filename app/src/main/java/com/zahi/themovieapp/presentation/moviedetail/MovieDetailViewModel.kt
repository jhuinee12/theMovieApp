package com.zahi.themovieapp.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zahi.themovieapp.base.BaseViewModel
import com.zahi.themovieapp.entity.*
import com.zahi.themovieapp.repositories.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : BaseViewModel() {

    private val _actorInfo = MutableLiveData<MutableList<ActorInfo>>()
    val actorInfo = _actorInfo

    val isFavorite = MutableLiveData<Int>()

    fun getMovieDetailInfo(movie_info: Int) {
        viewModelScope.launch {
            movieDetailRepository.getDetailMovieCredit(movie_info)
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

    fun addFavoriteMovie(movie: MovieInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailRepository.insert(movie)
            countMovieWhereId(movie.id)
        }
    }

    fun countMovieWhereId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isFavorite.postValue(movieDetailRepository.getCountId(id))
        }
    }

    fun deleteFavoriteMovie(movie: MovieInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailRepository.delete(movie)
            countMovieWhereId(movie.id)
        }
    }
}