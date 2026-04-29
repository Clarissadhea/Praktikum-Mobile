package com.example.list_xml

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.list_xml.databinding.ItemSongBinding
import com.example.list_xml.data.Song
import androidx.core.net.toUri

class SongAdapter(
    private val songs: List<Song>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvTitle.text = song.title
            tvArtist.text = song.artist
            tvYear.text = song.year
            imgAlbumCover.setImageResource(song.imageResId)

            tvAlbum.text = context.getString(R.string.label_album, song.album)

            btnListen.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, song.streamUrl.toUri())
                context.startActivity(intent)
            }

            btnDetail.setOnClickListener { onDetailClick(song.id) }
        }
    }

    override fun getItemCount() = songs.size
}