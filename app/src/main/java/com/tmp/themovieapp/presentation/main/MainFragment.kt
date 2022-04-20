package com.tmp.themovieapp.presentation.main

import com.google.android.material.tabs.TabLayoutMediator
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val tabTitle = arrayListOf("영화","배우")

    override fun initView() {
        binding.apply {
            this.viewPager.adapter = MainViewPagerAdapter(this@MainFragment)
            TabLayoutMediator(this.tabLayout, this.viewPager) { tab, position ->
                tab.text = tabTitle[position]
            }.attach()
        }
    }
}