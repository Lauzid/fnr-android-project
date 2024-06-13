package com.example.fnr_android_project.ui.card_recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.fnr_android_project.databinding.ActivityViewCardsBinding
import com.example.fnr_android_project.model.Set

class CardViewHolder(private val cardItemBinding: ActivityViewCardsBinding) :
    RecyclerView.ViewHolder(cardItemBinding.root) {
    fun bind(set: Set, onItemClicked: (Set) -> Unit) {
        cardItemBinding.apply {
            root.setOnClickListener { onItemClicked(set) }
            cardRecyclerView.text = set.name
        }
    }
}
