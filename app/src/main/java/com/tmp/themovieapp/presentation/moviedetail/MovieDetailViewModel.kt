package com.tmp.themovieapp.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.DetailMovieInfo
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.entity.MovieList
import com.tmp.themovieapp.repositories.MovieDetailRepository
import com.tmp.themovieapp.repositories.MovieListRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : BaseViewModel() {

    private val _movieDetailInfo = MutableLiveData<DetailMovieInfo>()
    val movieDetailInfo = _movieDetailInfo

    fun getMovieDetailInfo(movie_info: Int) {
        viewModelScope.launch {
            movieDetailRepository.getRepositories(movie_info)
                .enqueue(object : Callback<DetailMovieInfo> {
                    override fun onResponse(
                        call: Call<DetailMovieInfo>,
                        response: Response<DetailMovieInfo>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                Log.d("Repository", "Movies: ${responseBody.id}")
                            } else {
                                Log.d("Repository", "Failed to get response")
                            }
                        }
                    }

                    override fun onFailure(call: Call<DetailMovieInfo>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }
}