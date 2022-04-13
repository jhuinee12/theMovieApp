package com.tmp.themovieapp.presentation.movielist

import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.MovieListFragmentBinding

class MovieListFragment : BaseFragment<MovieListFragmentBinding>(R.layout.movie_list_fragment) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel

    override fun initView() {
        super.initView()
        binding.apply {  }
    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun initListener() {
        super.initListener()
    }

}