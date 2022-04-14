package com.tmp.themovieapp.presentation.moviedetail

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMovieDetailBinding
import com.tmp.themovieapp.presentation.movielist.MovieListViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieListViewModel
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun initView() {
        binding.apply {
            val arg = args.detail.get(0)

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w342${arg.poster_path}")
                .transform(CenterCrop())
                .into(this.image)

            this.title.text = arg.title
            this.overView.text = arg.overview
        }
    }

    override fun initViewModel() {
    }

}