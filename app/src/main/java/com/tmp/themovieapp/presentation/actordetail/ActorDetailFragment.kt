package com.tmp.themovieapp.presentation.actordetail

import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentActorDetailBinding

class ActorDetailFragment : BaseFragment<FragmentActorDetailBinding>(R.layout.fragment_actor_detail) {

    companion object {
        fun newInstance() = ActorDetailFragment()
    }

    private lateinit var viewModel: ActorDetailViewModel

    override fun initView() {
        binding.apply {  }
    }

    override fun initViewModel() {
    }

}