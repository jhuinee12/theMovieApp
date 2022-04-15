package com.tmp.themovieapp.presentation.moviedetail

import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentMovieDetailBinding
import com.tmp.themovieapp.entity.ActorInfo
import com.tmp.themovieapp.presentation.MainActivity
import com.tmp.themovieapp.repositories.MovieDetailRepository

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    // 영화 정보는 movie/popular 를 통해 받아온 정보를 argument로 넘겨줌
    // Cast 정보는 서버 통신해서 다시 받아옴
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieItem by lazy { args.detail.get(0) }

    private var actors = mutableListOf<ActorInfo>()

    private lateinit var actorListAdapter: ActorListAdapter

    override fun initView() {
        (activity as MainActivity).changeToolbar(true)

        binding.apply {
            this.movie = movieItem

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w342${movieItem.poster_path}")
                .transform(CenterCrop())
                .into(this.image)

            this.recyclerView.run {
                actorListAdapter = ActorListAdapter(actors).apply {
                    listener = object: ActorListAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            actorListAdapter.getItem(position).run {
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
                adapter = actorListAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = MovieDetailViewModelFactory(MovieDetailRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        viewModel.getMovieDetailInfo(movieItem.id)
        
        viewModel.actorInfo.observe(this) {
            actors = it
            actorListAdapter.update(actors)
            actorListAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
            else -> true
        }
    }
}