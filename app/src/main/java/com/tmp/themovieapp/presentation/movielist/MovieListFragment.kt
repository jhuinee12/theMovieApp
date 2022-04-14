package com.tmp.themovieapp.presentation.movielist

import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.MovieListFragmentBinding
import com.tmp.themovieapp.repositories.MoviesRepository

class MovieListFragment : BaseFragment<MovieListFragmentBinding>(R.layout.movie_list_fragment) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel

    override fun initView() {
        binding.apply {
            goToMovieDetail.setOnClickListener {
                findNavController().navigate(R.id.actionMovieListToMovieDetail)
            }
        }
    }

    override fun initViewModel() {
    }

    override fun initListener() {
        MoviesRepository.getPopularMovies()
    }

}