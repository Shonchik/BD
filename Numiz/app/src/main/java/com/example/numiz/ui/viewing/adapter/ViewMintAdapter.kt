package com.example.numiz.ui.viewing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numiz.databinding.ListViewBinding
import com.example.numiz.db.model.supmodel.MintModel


class ViewMintAdapter(private val itemClickCallback: (MintModel) -> Unit) :
    ListAdapter<MintModel, ViewMintAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: MintModel,
            clickCallback: (MintModel) -> Unit,
        ) {
            view.textView3.text = "${item.mint.name} (${item.country.name})"
            view.root.setOnClickListener { clickCallback(item) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], itemClickCallback)
    }

    object DiffCallback : DiffUtil.ItemCallback<MintModel>() {
        override fun areItemsTheSame(oldItem: MintModel, newItem: MintModel): Boolean {
            return oldItem.mint.id == newItem.mint.id
        }

        override fun areContentsTheSame(
            oldItem: MintModel,
            newItem: MintModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}