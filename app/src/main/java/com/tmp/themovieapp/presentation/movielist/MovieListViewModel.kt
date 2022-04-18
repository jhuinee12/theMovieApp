package com.tmp.themovieapp.presentation.movielist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.entity.MovieList
import com.tmp.themovieapp.repositories.MovieListRepository
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
                .enqueue(object : Callback<MovieList> {
                    override fun onResponse(
                        call: Call<MovieList>,
                        response: Response<MovieList>
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

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }
}