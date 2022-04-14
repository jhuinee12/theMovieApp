package com.tmp.themovieapp.presentation.movielist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.callback.MovieListRepositoryCallBack
import com.tmp.themovieapp.databinding.ItemMovieListBinding
import com.tmp.themovieapp.entity.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieListAdapter(private var movies:List<MovieInfo>): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    var listener: onClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false), listener)

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        Log.d("MovieListAdapter", "onBindViewHolder: called")

        val movie = movies[position]

        movie.run {
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
                .transform(CenterCrop())
                .into(holder.binding.image)

            holder.binding.title.text = movie.title
            holder.binding.overView.text = movie.overview
        }

    }

    override fun getItemCount(): Int = movies.size

    fun getItem(position: Int) = movies[position]

    fun update (updated: List<MovieInfo>) {
        CoroutineScope(Dispatchers.Main).launch {
            val diffResult = async(Dispatchers.IO) {
                getDiffResult(updated)
            }
            movies = updated
            diffResult.await().dispatchUpdatesTo(this@MovieListAdapter)
        }
    }

    private fun getDiffResult(updated:List<MovieInfo>): DiffUtil.DiffResult {
        val diffCallback = MovieListRepositoryCallBack(movies, updated)
        return DiffUtil.calculateDiff(diffCallback)
    }


    inner class MovieListViewHolder(view: View, listener: onClickListener?): RecyclerView.ViewHolder(view) {
        val binding = ItemMovieListBinding.bind(view)

        init {
            view.setOnClickListener {
                listener?.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface onClickListener {
        fun onItemClick(position: Int)
    }
}
