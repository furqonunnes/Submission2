package com.dicoding.submission2.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.submission2.data.model.User

class DiffUtil(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].login != newList[newItemPosition].login -> {
                false
            }
            oldList[oldItemPosition].avatar_url != newList[newItemPosition].avatar_url -> {
                false
            }
            else -> {
                true
            }
        }
    }
}