package com.example.numiz.ui.viewing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numiz.databinding.ListViewBinding
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.db.model.supmodel.MintModel

class ViewGlobalThemeAdapter(private val itemClickCallback: (GlobalThemeDbModel) -> Unit) :
    ListAdapter<GlobalThemeDbModel, ViewGlobalThemeAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: GlobalThemeDbModel,
            clickCallback: (GlobalThemeDbModel) -> Unit,
        ) {
            view.textView3.text = item.name
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

    object DiffCallback : DiffUtil.ItemCallback<GlobalThemeDbModel>() {
        override fun areItemsTheSame(oldItem: GlobalThemeDbModel, newItem: GlobalThemeDbModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GlobalThemeDbModel,
            newItem: GlobalThemeDbModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}