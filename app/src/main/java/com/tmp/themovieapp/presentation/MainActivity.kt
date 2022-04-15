package com.tmp.themovieapp.presentation

import android.util.Log
import android.view.View
import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val navHostFragment: NavHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
    }
    private val graph: NavGraph by lazy { navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph) }
    private val navController: NavController by lazy { navHostFragment.navController }

    override fun initView() {
        Log.d("TAG", "initView: 이곳은 Main")

        binding.apply { }
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
}