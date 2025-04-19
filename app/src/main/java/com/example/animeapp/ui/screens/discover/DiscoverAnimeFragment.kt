package com.example.animeapp.ui.screens.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.animeapp.databinding.FragmentDiscoverAnimeBinding
import com.example.animeapp.ui.screens.discover.adapters.DiscoverAnimeListAdapter
import com.example.animeapp.utils.collectOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoverAnimeFragment : Fragment() {

    private var _binding: FragmentDiscoverAnimeBinding? = null
    private val binding: FragmentDiscoverAnimeBinding get() = _binding!!
    private val discoverAnimeViewModel: DiscoverAnimeViewModel by viewModels()
    private val animeListAdapter = DiscoverAnimeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.animeListRecyclerView.adapter = animeListAdapter

        discoverAnimeViewModel.mangaStateFlow.collectOnStarted(viewLifecycleOwner){
            animeListAdapter.submitData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}