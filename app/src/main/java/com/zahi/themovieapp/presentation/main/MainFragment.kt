package com.zahi.themovieapp.presentation.main

import com.google.android.material.tabs.TabLayoutMediator
import com.zahi.themovieapp.R
import com.zahi.themovieapp.base.BaseFragment
import com.zahi.themovieapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private var mediator: TabLayoutMediator? = null
    private val tabTitle = arrayListOf("영화","배우")

    override fun initView() {
        (activity as MainActivity).changeBottomNav(true)
        
        binding.apply {
            mediator?.detach()

            this.viewPager.adapter = MainViewPagerAdapter(this@MainFragment)
            mediator = TabLayoutMediator(this.tabLayout, this.viewPager) { tab, position ->
                tab.text = tabTitle[position]
            }

            mediator?.attach()
        }
    }
}