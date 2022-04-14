package com.tmp.themovieapp.presentation.actordetail

import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentActorDetailBinding
import com.tmp.themovieapp.viewmodel.MainViewModel

class ActorDetailFragment : BaseFragment<FragmentActorDetailBinding>(R.layout.fragment_actor_detail) {

    companion object {
        fun newInstance() = ActorDetailFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun initView() {
        binding.apply {  }
    }

    override fun initViewModel() {
    }

}