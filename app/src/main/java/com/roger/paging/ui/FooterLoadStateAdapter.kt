package com.roger.paging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roger.paging.databinding.ItemFooterLoadingStateBinding

class FooterLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            ItemFooterLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            retry
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ViewHolder(val binding: ItemFooterLoadingStateBinding, val retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.loadStateRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.run {
                loadStateRetry.isVisible = loadState is LoadState.Error
                loadStateErrorMessage.isVisible = loadState is LoadState.Error
                loadStateProgress.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                    loadStateErrorMessage.text = loadState.error.localizedMessage
                }
            }
        }
    }
}