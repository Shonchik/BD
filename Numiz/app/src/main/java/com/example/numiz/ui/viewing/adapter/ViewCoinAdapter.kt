package com.example.numiz.ui.viewing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numiz.databinding.ListViewBinding
import com.example.numiz.db.model.supmodel.CoinModel

class ViewCoinAdapter(private val itemClickCallback: (CoinModel) -> Unit) :
    ListAdapter<CoinModel, ViewCoinAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: CoinModel,
            clickCallback: (CoinModel) -> Unit,
        ) {
            view.textView3.text = "${item.coin.name} (${item.mintModel.name})"
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

    object DiffCallback : DiffUtil.ItemCallback<CoinModel>() {
        override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem.coin.id == newItem.coin.id
        }

        override fun areContentsTheSame(
            oldItem: CoinModel,
            newItem: CoinModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}