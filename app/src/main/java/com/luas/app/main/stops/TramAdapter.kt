package com.luas.app.main.stops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luas.app.databinding.ItemTramBinding

class TramAdapter : ListAdapter<TramItemViewModel, TramAdapter.TramViewHolder>(
    Companion
) {

    companion object : DiffUtil.ItemCallback<TramItemViewModel>() {
        override fun areItemsTheSame(
            oldItem: TramItemViewModel,
            newItem: TramItemViewModel
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: TramItemViewModel,
            newItem: TramItemViewModel
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTramBinding.inflate(layoutInflater, parent, false)
        return TramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TramViewHolder, position: Int) {
        val currentTram = getItem(position)
        holder.binding.viewModel = currentTram
        holder.binding.executePendingBindings()
    }

    class TramViewHolder(val binding: ItemTramBinding) : RecyclerView.ViewHolder(binding.root)

}