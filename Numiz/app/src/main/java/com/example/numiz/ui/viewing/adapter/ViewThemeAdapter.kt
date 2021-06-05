package com.example.numiz.ui.viewing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numiz.databinding.ListViewBinding
import com.example.numiz.db.model.supmodel.MintModel
import com.example.numiz.db.model.supmodel.ThemeModel

class ViewThemeAdapter(private val itemClickCallback: (ThemeModel) -> Unit) :
    ListAdapter<ThemeModel, ViewThemeAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: ThemeModel,
            clickCallback: (ThemeModel) -> Unit,
        ) {
            view.textView3.text = "${item.theme.name} (${item.globalTheme.name})"
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

    object DiffCallback : DiffUtil.ItemCallback<ThemeModel>() {
        override fun areItemsTheSame(oldItem: ThemeModel, newItem: ThemeModel): Boolean {
            return oldItem.theme.id == newItem.theme.id
        }

        override fun areContentsTheSame(
            oldItem: ThemeModel,
            newItem: ThemeModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}