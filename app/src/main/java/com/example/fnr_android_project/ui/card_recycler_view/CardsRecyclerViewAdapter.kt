package com.example.fnr_android_project.ui.card_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fnr_android_project.databinding.ActivityViewCardsBinding
import com.example.fnr_android_project.model.Set
import com.example.fnr_android_project.ui.collection_recycler_view.CollectionViewHolder

class CardsRecyclerViewAdapter(private val collections: List<Set>, private val onClick: (Set) -> Unit):
    RecyclerView.Adapter<CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = ActivityViewCardsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(collections[position], onItemClicked = onClick)
    }

    override fun getItemCount(): Int = collections.size
}