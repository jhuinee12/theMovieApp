package com.tmp.themovieapp.callback

import androidx.recyclerview.widget.DiffUtil
import com.tmp.themovieapp.entity.MovieInfo

class MovieListRepositoryCallBack (private val oldList: List<MovieInfo>, private val newList: List<MovieInfo>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}