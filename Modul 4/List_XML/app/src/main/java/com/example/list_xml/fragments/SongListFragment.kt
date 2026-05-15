package com.example.list_xml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.list_xml.R
import com.example.list_xml.adapters.FeaturedSongAdapter
import com.example.list_xml.adapters.SongAdapter
import com.example.list_xml.databinding.FragmentSongListBinding
import com.example.list_xml.domain.model.Song
import com.example.list_xml.viewModel.SongViewModel
import com.example.list_xml.viewModel.SongViewModelFactory
import kotlinx.coroutines.launch

class SongListFragment : Fragment() {

    private var _binding: FragmentSongListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongViewModel by viewModels {
        SongViewModelFactory("Kategori: Dhea's Top Songs")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTop5.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvAllSongs.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.songs.collect { songList ->
                    setupAdapters(songList)
                }
            }
        }
    }

    private fun setupAdapters(songList: List<Song>) {
        val top5Songs = songList.take(5)

        val featuredAdapter = FeaturedSongAdapter(top5Songs) { song ->
            viewModel.onDetailClick(song)
            navigateToDetail(song.id)
        }
        binding.rvTop5.adapter = featuredAdapter

        val allSongsAdapter = SongAdapter(
            songs = songList,
            onDetailClick = { song ->
                viewModel.onDetailClick(song)
                navigateToDetail(song.id)
            },
            onListenClick = { url ->
                viewModel.onListenClick(url)
            }
        )
        binding.rvAllSongs.adapter = allSongsAdapter
    }

    private fun navigateToDetail(songId: Int) {
        val bundle = Bundle().apply { putInt("songId", songId) }
        findNavController().navigate(R.id.action_songListFragment_to_songDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}