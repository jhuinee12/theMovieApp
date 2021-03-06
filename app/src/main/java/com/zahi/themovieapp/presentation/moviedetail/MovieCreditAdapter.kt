package com.zahi.themovieapp.presentation.moviedetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.zahi.themovieapp.R
import com.zahi.themovieapp.callback.ActorListRepositoryCallBack
import com.zahi.themovieapp.databinding.ItemMovieCreditBinding
import com.zahi.themovieapp.entity.ActorInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieCreditAdapter(private var actors:List<ActorInfo>): RecyclerView.Adapter<MovieCreditAdapter.MovieCreditViewHolder>() {

    var listener: onClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCreditViewHolder =
        MovieCreditViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_credit, parent, false), listener)

    override fun onBindViewHolder(holder: MovieCreditViewHolder, position: Int) {
        Log.d("MovieCreditAdapter", "onBindViewHolder: called")

        val actor = actors[position]

        actor.run {
            holder.binding.actor = this

            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w342${this.profile_path}")
                .error(R.drawable.ic_no_image)
                .transform(CenterCrop())
                .into(holder.binding.image)
        }

    }

    override fun getItemCount(): Int = actors.size

    fun getItem(position: Int) = actors[position]

    fun update (updated: List<ActorInfo>) {
        CoroutineScope(Dispatchers.Main).launch {
            val diffResult = async(Dispatchers.IO) {
                getDiffResult(updated)
            }
            diffResult.await().run{
                actors = updated
                dispatchUpdatesTo(this@MovieCreditAdapter)
            }
        }
    }

    private fun getDiffResult(updated:List<ActorInfo>): DiffUtil.DiffResult {
        val diffCallback = ActorListRepositoryCallBack(actors, updated)
        return DiffUtil.calculateDiff(diffCallback)
    }


    inner class MovieCreditViewHolder(view: View, listener: onClickListener?): RecyclerView.ViewHolder(view) {
        val binding = ItemMovieCreditBinding.bind(view)

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
