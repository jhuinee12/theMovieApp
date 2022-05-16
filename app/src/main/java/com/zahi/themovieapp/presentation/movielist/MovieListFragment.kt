package com.zahi.themovieapp.presentation.movielist

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zahi.themovieapp.R
import com.zahi.themovieapp.base.BaseFragment
import com.zahi.themovieapp.databinding.FragmentMovieListBinding
import com.zahi.themovieapp.entity.MovieInfo
import com.zahi.themovieapp.presentation.main.MainActivity
import com.zahi.themovieapp.presentation.main.MainFragmentDirections
import com.zahi.themovieapp.presentation.main.WrapContentGridLayoutMangager
import com.zahi.themovieapp.repositories.MovieListRepository

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory
    private lateinit var movieListAdapter: MovieListAdapter

    private var movieList: MutableList<MovieInfo> = mutableListOf()

    private var pageCount = 1

    override fun initView() {

        (activity as MainActivity).changeToolbar(false)

        binding.apply {
            this.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager?)!!.findLastVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                    if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        viewModel.page.value = ++pageCount
                    }
                }
            })

            this.recyclerView.run {
                movieListAdapter = MovieListAdapter(movieList).apply {
                    listener = object: MovieListAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            movieListAdapter.getItem(position).run {
                                findNavController().navigate(
                                    MainFragmentDirections.actionMovieListToMovieDetail(arrayOf(
                                        this
                                    ))
                                )
                            }
                        }
                    }
                }
                setHasFixedSize(true)
                layoutManager = WrapContentGridLayoutMangager(requireContext(), 2)
                adapter = movieListAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = MovieListViewModelFactory(MovieListRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.page.value = pageCount

        viewModel.page.observe(this) {
            when ((activity as MainActivity).iconSelectedInBottomNav) {
                R.id.action_home -> viewModel.getPopularMovies()
                R.id.action_favorite -> viewModel.getMyFavoriteMovies()
            }
        }

        viewModel.movieList.observe(this) {
            movieListAdapter.update(it)
            movieList = it
        }
    }


}