package com.tmp.themovieapp.presentation.movielist

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMovieListBinding
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.repositories.MovieListRepository

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory
    private lateinit var movieListAdapter: MovieListAdapter

    private var movieData = mutableListOf<MovieInfo>()

    private var pageCount = 1

    override fun initView() {
        binding.apply {
            this.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                    if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        pageCount++
                        viewModel.page.value = pageCount + 1
                    }
                }
            })

            this.recyclerView.run {
                movieListAdapter = MovieListAdapter(movieData)
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = movieListAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = MovieListViewModelFactory(MovieListRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.page.value = pageCount

        viewModel.page.observe(this) {
            viewModel.getPopularMovies()
        }

        viewModel.movieList.observe(this) {
            movieData = it
            updateRepositories(it)
        }
    }

    private fun updateRepositories(movies: List<MovieInfo>) {
        if (::movieListAdapter.isInitialized) {
            movieListAdapter.update(movies)
        } else {
            movieListAdapter = MovieListAdapter(movies).apply {
                listener = object: MovieListAdapter.onClickListener {
                    override fun onItemClick(position: Int) {
                        movieListAdapter.getItem(position).run {
                            findNavController().navigate(
                                MovieListFragmentDirections.actionMovieListToMovieDetail(arrayOf(
                                    this
                                ))
                            )
                        }
                    }
                }
            }
        }

        movieListAdapter.notifyDataSetChanged()
    }

}