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
        MovieListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false))

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        Log.d("MovieListAdapter", "onBindViewHolder: called")

        val movie = movies[position]

        movie.run {
            holder.binding.movie = this

            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w342${this.poster_path}")
                .error(R.drawable.ic_no_image)
                .transform(CenterCrop())
                .into(holder.binding.image)

            holder.itemView.setOnClickListener {
                listener?.onItemClick(position)
            }
        }

    }

    override fun getItemCount(): Int = movies.size

    fun getItem(position: Int) = movies[position]

    fun update (updated: List<MovieInfo>) { // updated : 변화된 리스트
        CoroutineScope(Dispatchers.Main).launch {   // launch : 결과 반환 X && Dispatchers.Main : UI와 상호작용하는 작업 실행
            val diffResult = async(Dispatchers.IO) {    // async : 결과 반환 && Dispatchers.IO : 네트워크 I/O 작업 실행
                getDiffResult(updated)
            }
            diffResult.await().run{ // await() : 값을 반환하기 전에 async 작업이 모두 완료되도록 보장
                movies = updated    // movies(updapter와 연결된 리스트)에 변화가 감지된 updated를 넣는다
                dispatchUpdatesTo(this@MovieListAdapter) // 리스트 갱신
            }
        }
    }

    private fun getDiffResult(updated:List<MovieInfo>): DiffUtil.DiffResult {
        val diffCallback = MovieListRepositoryCallBack(movies, updated) // movies: oldList , updated: newList
        return DiffUtil.calculateDiff(diffCallback)
    }


    inner class MovieListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMovieListBinding.bind(view)
    }

    interface onClickListener {
        fun onItemClick(position: Int)
    }
}
