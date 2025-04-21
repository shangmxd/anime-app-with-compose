package com.example.animeapp.ui.common

import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.databinding.DiscoverAnimeLitItemsBinding

class MyViewHolder(
    val binding: DiscoverAnimeLitItemsBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root)