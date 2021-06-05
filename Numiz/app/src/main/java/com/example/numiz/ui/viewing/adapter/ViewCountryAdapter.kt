package com.example.numiz.ui.viewing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numiz.databinding.ListViewBinding
import com.example.numiz.db.model.CountryDbModel

class ViewCountryAdapter(private val itemClickCallback: (CountryDbModel) -> Unit) :
    ListAdapter<CountryDbModel, ViewCountryAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: CountryDbModel,
            clickCallback: (CountryDbModel) -> Unit,
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

    object DiffCallback : DiffUtil.ItemCallback<CountryDbModel>() {
        override fun areItemsTheSame(oldItem: CountryDbModel, newItem: CountryDbModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CountryDbModel,
            newItem: CountryDbModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}