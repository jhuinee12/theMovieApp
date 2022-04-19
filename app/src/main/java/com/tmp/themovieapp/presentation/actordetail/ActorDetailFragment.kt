package com.tmp.themovieapp.presentation.actordetail

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentActorDetailBinding
import com.tmp.themovieapp.presentation.main.MainActivity

class ActorDetailFragment : BaseFragment<FragmentActorDetailBinding>(R.layout.fragment_actor_detail) {

    companion object {
        fun newInstance() = ActorDetailFragment()
    }

    private val args by navArgs<ActorDetailFragmentArgs>()
    private val args_actor by lazy { args.detail.get(0) }

    override fun initView() {
        (activity as MainActivity).changeToolbar(true)

        binding.apply {
            binding.actor = args_actor

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w342${args_actor.profile_path}")
                .transform(CenterCrop())
                .into(this.image)
        }
    }

    override fun initViewModel() {
    }
}