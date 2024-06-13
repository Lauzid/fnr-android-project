package com.example.fnr_android_project.ui.collection_recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.fnr_android_project.databinding.CollectionItemBinding
import com.example.fnr_android_project.model.Set

class CollectionViewHolder(private val collectionItemBinding: CollectionItemBinding) :
    RecyclerView.ViewHolder(collectionItemBinding.root) {
    fun bind(set: Set, onItemClicked: (Set) -> Unit) {
        collectionItemBinding.apply {
            root.setOnClickListener { (onItemClicked(set)) }
            collectionName.text = set.name
        }
    }
}
