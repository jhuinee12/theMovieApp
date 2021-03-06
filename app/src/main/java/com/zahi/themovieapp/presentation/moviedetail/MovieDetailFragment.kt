package com.zahi.themovieapp.presentation.moviedetail

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.zahi.themovieapp.R
import com.zahi.themovieapp.base.BaseFragment
import com.zahi.themovieapp.databinding.FragmentMovieDetailBinding
import com.zahi.themovieapp.entity.ActorInfo
import com.zahi.themovieapp.presentation.main.MainActivity
import com.zahi.themovieapp.repositories.MovieDetailRepository

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    private var actorList: MutableList<ActorInfo> = mutableListOf()

    // 영화 정보는 movie/popular 를 통해 받아온 정보를 argument로 넘겨줌
    // Cast 정보는 서버 통신해서 다시 받아옴
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieItem by lazy { args.detail.get(0) }

    private lateinit var movieCreditAdapter: MovieCreditAdapter

    override fun initView() {
        (activity as MainActivity).changeToolbar(true)
        (activity as MainActivity).changeBottomNav(false)

        binding.apply {
            this.movie = movieItem

            looksFavoriteButton()

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w342${movieItem.poster_path}")
                .transform(CenterCrop())
                .into(this.image)

            this.recyclerView.run {
                movieCreditAdapter = MovieCreditAdapter(actorList).apply {
                    listener = object: MovieCreditAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            movieCreditAdapter.getItem(position).run {
                                findNavController().navigate(
                                    MovieDetailFragmentDirections.actionMovieDetailToActorDetail(arrayOf(
                                        this
                                    ))
                                )
                            }
                        }
                    }
                }

                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieCreditAdapter
            }

            binding.favoriteOff.setOnClickListener {
                viewModel.addFavoriteMovie(movieItem)
            }
            binding.favoriteOn.setOnClickListener {
                viewModel.deleteFavoriteMovie(movieItem)
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = MovieDetailViewModelFactory(MovieDetailRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        viewModel.getMovieDetailInfo(movieItem.id)

        viewModel.countMovieWhereId(movieItem.id)

        viewModel.isFavorite.observe(this) {
            looksFavoriteButton()
        }
        
        viewModel.actorInfo.observe(this) {
            movieCreditAdapter.update(it)
            actorList = it
        }
    }

    fun looksFavoriteButton() {
        if (viewModel.isFavorite.value != 0) {
            binding.favoriteOn.visibility = View.VISIBLE
            binding.favoriteOff.visibility = View.GONE
        } else {
            binding.favoriteOn.visibility = View.GONE
            binding.favoriteOff.visibility = View.VISIBLE
        }
    }
}