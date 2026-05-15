package com.example.list_xml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.list_xml.R
import com.example.list_xml.databinding.FragmentSongDetailBinding
import com.example.list_xml.viewModel.SongViewModel
import com.example.list_xml.viewModel.SongViewModelFactory

class SongDetailFragment : Fragment() {
    private var _binding: FragmentSongDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongViewModel by viewModels {
        SongViewModelFactory("Kategori: Dhea's Top Songs")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songId = arguments?.getInt("songId") ?: return
        val song = viewModel.getSongById(songId)

        song?.let {
            binding.imgDetailCover.setImageResource(it.imageResId)
            binding.tvDetailTitle.text = it.title
            binding.tvDetailArtist.text = it.artist
            binding.tvDetailAlbum.text = getString(R.string.label_album, it.album)
            binding.tvDetailYear.text = getString(R.string.label_release_year, it.year)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}