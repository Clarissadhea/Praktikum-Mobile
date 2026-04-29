package com.example.list_xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.list_xml.databinding.FragmentSongListBinding
import com.example.list_xml.data.SongDataDummy

class SongListFragment : Fragment() {

    private var _binding: FragmentSongListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val top5Songs = SongDataDummy.dummySongs.take(5)
        val featuredAdapter = FeaturedSongAdapter(top5Songs) { songId ->
            navigateToDetail(songId)
        }
        binding.rvTop5.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTop5.adapter = featuredAdapter

        val allSongsAdapter = SongAdapter(SongDataDummy.dummySongs) { songId ->
            navigateToDetail(songId)
        }
        binding.rvAllSongs.layoutManager = LinearLayoutManager(requireContext())
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