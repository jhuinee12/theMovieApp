package com.zahi.themovieapp.callback

import androidx.recyclerview.widget.DiffUtil
import com.zahi.themovieapp.entity.ActorInfo

class ActorListRepositoryCallBack (private val oldList: List<ActorInfo>, private val newList: List<ActorInfo>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}