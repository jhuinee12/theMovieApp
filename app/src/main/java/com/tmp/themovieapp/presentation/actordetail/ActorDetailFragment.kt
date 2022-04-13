package com.tmp.themovieapp.presentation.actordetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment

class ActorDetailFragment : BaseFragment(R.layout.actor_detail_fragment) {

    companion object {
        fun newInstance() = ActorDetailFragment()
    }

    private lateinit var viewModel: ActorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actor_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActorDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}