package com.tmp.themovieapp.presentation.movielist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tmp.themovieapp.base.BaseViewModel
import com.tmp.themovieapp.entity.DetailMovieInfo
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.entity.MovieList
import com.tmp.themovieapp.repositories.MovieListRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(private val movieListRepository: MovieListRepository) : BaseViewModel() {

    private val _movieList = MutableLiveData<List<MovieInfo>>()
    val movieList = _movieList

    fun getPopularMovies(page: Int = 1) {
        viewModelScope.launch {
            movieListRepository.getRepositories(page)
                .enqueue(object : Callback<MovieList> {
                    override fun onResponse(
                        call: Call<MovieList>,
                        response: Response<MovieList>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                Log.d("Repository", "Movies: ${responseBody.movies}")
                                _movieList.postValue(responseBody.movies)
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