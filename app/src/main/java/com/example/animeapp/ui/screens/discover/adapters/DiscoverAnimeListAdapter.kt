package com.example.animeapp.ui.screens.discover.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.animeapp.databinding.DiscoverAnimeLitItemsBinding
import com.example.animeapp.model.local.Anime

class DiscoverAnimeListAdapter :ListAdapter<Anime,DiscoverAnimeListAdapter.MyViewHolder>(simpleDiffUtil) {

    companion object {
        val simpleDiffUtil = object :DiffUtil.ItemCallback<Anime>(){
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.malID == newItem.malID
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class MyViewHolder(val binding: DiscoverAnimeLitItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DiscoverAnimeLitItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.animeTitleTv.text = item.title
        holder.binding.animeBackgroundTv.text = item.synopsis
        holder.binding.animeImgIv.load(item.images.jpg.imageUrl)
        holder.binding.animeReleaseYearTv.text = item.year.toString()
        holder.binding.animeEpisodesTv.text = item.episodes.toString()
        holder.binding.animeCountTv.text = position.plus(1).toString()
    }

}