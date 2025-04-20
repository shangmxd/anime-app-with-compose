package com.example.animeapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.animeapp.model.local.Anime

val simpleDiffUtil = object : DiffUtil.ItemCallback<Anime>(){
    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.malID == newItem.malID
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem == newItem
    }

}