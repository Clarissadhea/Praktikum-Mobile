package com.example.list_xml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.list_xml.databinding.ItemSongFeaturedBinding
import com.example.list_xml.data.Song

class FeaturedSongAdapter(
    private val songs: List<Song>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<FeaturedSongAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSongFeaturedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSongFeaturedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        with(holder.binding) {
            tvFeaturedTitle.text = song.title
            tvFeaturedArtist.text = song.artist
            imgFeaturedCover.setImageResource(song.imageResId)

            btnFeaturedDetail.setOnClickListener { onDetailClick(song.id) }
        }
    }

    override fun getItemCount() = songs.size
}