package com.example.numiz.ui.viewing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numiz.databinding.ListViewBinding
import com.example.numiz.db.model.supmodel.CollectionModel

class ViewCollectionAdapter(private val itemClickCallback: (CollectionModel) -> Unit) :
    ListAdapter<CollectionModel, ViewCollectionAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: CollectionModel,
            clickCallback: (CollectionModel) -> Unit,
        ) {
            view.textView3.text = "${item.collection.name} (${item.theme.name})"
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

    object DiffCallback : DiffUtil.ItemCallback<CollectionModel>() {
        override fun areItemsTheSame(oldItem: CollectionModel, newItem: CollectionModel): Boolean {
            return oldItem.collection.id == newItem.collection.id
        }

        override fun areContentsTheSame(
            oldItem: CollectionModel,
            newItem: CollectionModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}