package com.tmp.themovieapp.presentation.moviedetail

import androidx.navigation.fragment.findNavController
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun initView() {
        binding.apply {
            goToActorDetail.setOnClickListener {
                findNavController().navigate(R.id.actionMovieDetailToActorDetail)
            }
        }
    }

    override fun initViewModel() {
    }

}