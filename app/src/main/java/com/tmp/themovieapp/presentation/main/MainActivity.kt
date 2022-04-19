package com.tmp.themovieapp.presentation.main

import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivityMainBinding
import com.tmp.themovieapp.presentation.movielist.MovieListFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val navHostFragment: NavHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
    }
    private val graph: NavGraph by lazy { navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph) }
    private val navController: NavController by lazy { navHostFragment.navController }

    private lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun initView() {
        Log.d("TAG", "initView: 이곳은 Main")

        binding.apply {
            this.tabLayout.addTab(tabLayout.newTab().setText("영화"))
            this.tabLayout.addTab(tabLayout.newTab().setText("배우"))
            
            this.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> MovieListFragment()
                        1 -> MovieListFragment()
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) { }
                override fun onTabReselected(tab: TabLayout.Tab?) { }
            })
        }
    }

    override fun initViewModel() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun changeToolbar(
        isVisible: Boolean = true
    ) {
        binding.layoutToolbar.visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }

        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
            else -> true
        }
    }
}