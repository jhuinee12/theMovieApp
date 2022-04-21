package com.tmp.themovieapp.presentation.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tmp.themovieapp.presentation.actorlist.ActorListFragment
import com.tmp.themovieapp.presentation.movielist.MovieListFragment

class MainViewPagerAdapter(fragmentActivity: MainFragment) : FragmentStateAdapter(fragmentActivity) {

    private val movieListFragment by lazy { MovieListFragment.newInstance() }
    private val actorListFragment by lazy { ActorListFragment.newInstance() }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment  =
        when (position) {
            0 -> movieListFragment
            1 -> actorListFragment
            else -> movieListFragment
        }
}