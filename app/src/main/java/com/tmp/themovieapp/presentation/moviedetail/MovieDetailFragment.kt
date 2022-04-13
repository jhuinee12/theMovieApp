package com.tmp.themovieapp.presentation.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.MovieDetailFragmentBinding

class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding>(R.layout.movie_detail_fragment) {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieDetailViewModel

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