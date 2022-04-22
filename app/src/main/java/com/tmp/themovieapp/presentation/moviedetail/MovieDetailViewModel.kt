package com.tmp.themovieapp.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.*
import com.tmp.themovieapp.repositories.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : BaseViewModel() {

    private val _actorInfo = MutableLiveData<MutableList<ActorInfo>>()
    val actorInfo = _actorInfo

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
        }
    }

    fun countMovieWhereId(id: Int): Int {
        var count = 0
        viewModelScope.launch(Dispatchers.IO) {
            count = movieDetailRepository.getCountId(id)
        }
        return count
    }

    fun deleteFavoriteMovie(movie: MovieInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailRepository.delete(movie)
        }
    }
}