package com.tmp.themovieapp.presentation.actorlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.tmp.themovieapp.R
import com.tmp.themovieapp.callback.ActorListRepositoryCallBack
import com.tmp.themovieapp.callback.MovieListRepositoryCallBack
import com.tmp.themovieapp.databinding.ItemMovieCreditBinding
import com.tmp.themovieapp.databinding.ItemMovieListBinding
import com.tmp.themovieapp.entity.ActorInfo
import com.tmp.themovieapp.entity.MovieInfo
import com.tmp.themovieapp.presentation.moviedetail.MovieCreditAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ActorListAdapter(private var actors: List<ActorInfo>): RecyclerView.Adapter<ActorListAdapter.ActorListViewHolder>() {

    var listener: onClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListViewHolder =
        ActorListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_credit, parent, false), listener)

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
        Log.d("ActorListAdapter", "onBindViewHolder: called")

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
                dispatchUpdatesTo(this@ActorListAdapter)
            }
        }
    }

    private fun getDiffResult(updated:List<ActorInfo>): DiffUtil.DiffResult {
        val diffCallback = ActorListRepositoryCallBack(actors, updated)
        return DiffUtil.calculateDiff(diffCallback)
    }


    inner class ActorListViewHolder(view: View, listener: onClickListener?): RecyclerView.ViewHolder(view) {
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
