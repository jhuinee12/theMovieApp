package com.tmp.themovieapp.presentation.main

import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding4.view.detaches
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private var mediator: TabLayoutMediator? = null
    private val tabTitle = arrayListOf("영화","배우")

    override fun initView() {
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