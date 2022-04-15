package com.tmp.themovieapp.presentation.actordetail

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentActorDetailBinding
import com.tmp.themovieapp.presentation.moviedetail.MovieDetailFragmentArgs
import com.tmp.themovieapp.presentation.movielist.MovieListViewModel

class ActorDetailFragment : BaseFragment<FragmentActorDetailBinding>(R.layout.fragment_actor_detail) {

    companion object {
        fun newInstance() = ActorDetailFragment()
    }

    private lateinit var viewModel: MovieListViewModel

    private val args by navArgs<ActorDetailFragmentArgs>()
    private val actor by lazy { args.detail.get(0) }

    override fun initView() {
        binding.apply {
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w342${actor.profile_path}")
                .transform(CenterCrop())
                .into(this.image)

            this.name.text = actor.name
            this.charactor.text = actor.character
        }
    }

    override fun initViewModel() {
    }

}