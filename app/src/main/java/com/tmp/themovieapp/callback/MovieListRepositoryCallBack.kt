package com.tmp.themovieapp.callback

import androidx.recyclerview.widget.DiffUtil
import com.tmp.themovieapp.entity.MovieInfo

class MovieListRepositoryCallBack (private val oldList: List<MovieInfo>, private val newList: List<MovieInfo>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size   // 현재 리스트에 노출하고 있는 리스트 사이즈
    override fun getNewListSize(): Int = newList.size   // 새로 갱신할 리스트 사이즈
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id  // 현재 리스트의 아이템과 새로운 아이템이 같은지 비교
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]    // 현재 리스트 아이템과 새로운 아이템 equals 비교
}