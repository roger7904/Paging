package com.roger.paging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roger.paging.databinding.ItemPeopleBinding
import com.roger.paging.model.entity.PeopleList

class PeopleInfoAdapter :
    PagingDataAdapter<PeopleList.People, PeopleInfoAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPeopleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.onBind(it) }
    }

    inner class ViewHolder(val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(value: PeopleList.People) {
            binding.run {
                tvTimeContent.text = value.statistic_yyymm
                tvPlaceContent.text = "${value.site_id}${value.village}"
                tvBornContent.text = value.birth_total
                tvDeathContent.text = value.death_total
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PeopleList.People>() {
        override fun areItemsTheSame(
            oldItem: PeopleList.People,
            newItem: PeopleList.People
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PeopleList.People,
            newItem: PeopleList.People
        ): Boolean {
            return oldItem == newItem
        }
    }
}