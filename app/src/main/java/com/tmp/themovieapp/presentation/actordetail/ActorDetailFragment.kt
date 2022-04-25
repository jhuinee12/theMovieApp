package com.tmp.themovieapp.presentation.actordetail

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentActorDetailBinding
import com.tmp.themovieapp.presentation.main.MainActivity
import com.tmp.themovieapp.presentation.moviedetail.MovieDetailViewModel
import com.tmp.themovieapp.presentation.moviedetail.MovieDetailViewModelFactory
import com.tmp.themovieapp.repositories.ActorDetailRepository
import com.tmp.themovieapp.repositories.MovieDetailRepository

class ActorDetailFragment : BaseFragment<FragmentActorDetailBinding>(R.layout.fragment_actor_detail) {

    private lateinit var viewModel: ActorDetailViewModel
    private lateinit var viewModelFactory: ActorDetailViewModelFactory

    private val args by navArgs<ActorDetailFragmentArgs>()
    private val args_actor by lazy { args.detail.get(0) }

    override fun initView() {
        (activity as MainActivity).changeToolbar(true)
        (activity as MainActivity).changeBottomNav(false)

        binding.apply {
            binding.actor = args_actor

            looksFavoriteButton()

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w342${args_actor.profile_path}")
                .transform(CenterCrop())
                .into(this.image)

            binding.favoriteOff.setOnClickListener {
                viewModel.addFavoriteMovie(args_actor)
            }
            binding.favoriteOn.setOnClickListener {
                viewModel.deleteFavoriteMovie(args_actor)
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = ActorDetailViewModelFactory(ActorDetailRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(ActorDetailViewModel::class.java)

        viewModel.countMovieWhereId(args_actor.id)

        viewModel.isFavorite.observe(this) {
            looksFavoriteButton()
        }
    }

    fun looksFavoriteButton() {
        if (viewModel.isFavorite.value != 0) {
            binding.favoriteOn.visibility = View.VISIBLE
            binding.favoriteOff.visibility = View.GONE
        } else {
            binding.favoriteOn.visibility = View.GONE
            binding.favoriteOff.visibility = View.VISIBLE
        }
    }
}