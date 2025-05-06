package com.example.animeapp.ui.common

import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.databinding.DiscoverAnimeListItemsBinding

class MyViewHolder(
    val binding: DiscoverAnimeListItemsBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root)