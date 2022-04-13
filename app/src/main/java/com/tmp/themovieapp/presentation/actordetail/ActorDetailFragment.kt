package com.tmp.themovieapp.presentation.actordetail

import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.ActorDetailFragmentBinding

class ActorDetailFragment : BaseFragment<ActorDetailFragmentBinding>(R.layout.actor_detail_fragment) {

    companion object {
        fun newInstance() = ActorDetailFragment()
    }

    private lateinit var viewModel: ActorDetailViewModel

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