package com.example.animeapp.ui.screens.saved.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil3.load
import com.example.animeapp.databinding.DiscoverAnimeListItemsBinding
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.ui.common.MyViewHolder
import com.example.animeapp.utils.simpleDiffUtil

class SavedAnimeListAdapter(
    private val onAnimeClicked: (Int) -> Unit
) : ListAdapter<Anime, MyViewHolder>(simpleDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DiscoverAnimeListItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(
            binding = binding,
            onClick = onAnimeClicked
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.animeTitleTv.text = item?.title
        holder.binding.animeBackgroundTv.text = item?.synopsis
        holder.binding.animeImgIv.load(item?.images?.jpg?.imageUrl)
        holder.binding.animeReleaseYearTv.text = item?.year.toString()
        holder.binding.animeEpisodesTv.text = item?.episodes.toString()
        holder.binding.animeCountTv.visibility = View.GONE
        holder.binding.root.setOnClickListener {
            onAnimeClicked(item.malID)
        }
    }
}