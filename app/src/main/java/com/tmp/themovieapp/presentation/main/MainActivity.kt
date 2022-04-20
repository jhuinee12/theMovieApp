package com.tmp.themovieapp.presentation.main

import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseActivity
import com.tmp.themovieapp.databinding.ActivityMainBinding
import com.tmp.themovieapp.presentation.movielist.MovieListFragment
import java.lang.IndexOutOfBoundsException


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val navHostFragment: NavHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
    }
    private val graph: NavGraph by lazy { navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph) }
    private val navController: NavController by lazy { navHostFragment.navController }

    override fun initView() {
        Log.d("TAG", "initView: 이곳은 Main")

        binding.apply {
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //navController.navigateUp()
    }

    override fun initViewModel() {
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
class WrapContentGridLayoutMangager(context:Context, count: Int): GridLayoutManager(context, count) {
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("MainActivity", "onLayoutChildren: IndexOutOfBoundsException")
        }
    }
}
