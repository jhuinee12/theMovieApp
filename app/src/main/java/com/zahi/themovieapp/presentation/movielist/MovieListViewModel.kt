package com.zahi.themovieapp.presentation.movielist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zahi.themovieapp.base.BaseViewModel
import com.zahi.themovieapp.entity.MovieInfo
import com.zahi.themovieapp.entity.PopularMovieList
import com.zahi.themovieapp.repositories.MovieListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(private val movieListRepository: MovieListRepository) : BaseViewModel() {

    private val _movieList = MutableLiveData<MutableList<MovieInfo>>()
    val tempList = mutableListOf<MovieInfo>()
    val movieList = _movieList

    var page: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getPopularMovies() {
        viewModelScope.launch {
            movieListRepository.getRepositories(page.value!!)
                .enqueue(object : Callback<PopularMovieList> {
                    override fun onResponse(
                        call: Call<PopularMovieList>,
                        response: Response<PopularMovieList>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                Log.d("Repository", "Movies: ${responseBody.movies}")
                                tempList.addAll(responseBody.movies)
                                _movieList.postValue(tempList.toMutableList())
                            } else {
                                Log.d("Repository", "Failed to get response")
                            }
                        }
                    }

                    override fun onFailure(call: Call<PopularMovieList>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }

    fun getMyFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieList.postValue(movieListRepository.getAll())
        }
    }
}