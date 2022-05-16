package com.zahi.themovieapp.presentation.main

import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zahi.themovieapp.R
import com.zahi.themovieapp.base.BaseActivity
import com.zahi.themovieapp.databinding.ActivityMainBinding
import java.lang.IndexOutOfBoundsException


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    private val navHostFragment: NavHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
    }
    private val graph: NavGraph by lazy { navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph) }
    private val navController: NavController by lazy { navHostFragment.navController }

//    val iconSelectedInBottomNav by lazy { viewModel.bottomNavItem }
    var iconSelectedInBottomNav = R.id.action_home

    override fun initView() {
        Log.d("TAG", "initView: 이곳은 Main")

        binding.apply {
            this.bottomNav.setOnItemSelectedListener {
                refreshCurrentFragment()
                iconSelectedInBottomNav = it.itemId
                //viewModel.bottomNavItem.postValue(it.itemId)
                return@setOnItemSelectedListener true
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        /*viewModel.bottomNavItem.observe(this) {
            refreshCurrentFragment()
        }*/
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

    fun changeBottomNav(isVisible: Boolean = false) {
        binding.bottomNav.visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun refreshCurrentFragment(){
        val id = navController.currentDestination?.id
        navController.popBackStack(id!!,true)
        navController.navigate(id)
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
