package com.tmp.themovieapp.presentation.movielist

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMovieListBinding
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.viewmodel.MainViewModelFactory
import com.tmp.themovieapp.repositories.MovieListRepository
import com.tmp.themovieapp.viewmodel.MainViewModel

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var movieListAdapter: MovieListAdapter

    override fun initView() {
        binding.apply {
        }
    }

    override fun initViewModel() {
        viewModelFactory = MainViewModelFactory(MovieListRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPopularMovies()

        viewModel.movieList.observe(this) {
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
                            findNavController().navigate(R.id.actionMovieListToMovieDetail)
                        }
                    }
                }
            }
        }

        binding.recyclerView.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieListAdapter
        }
    }

}