package com.example.animeapp.ui.screens.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.animeapp.databinding.FragmentDiscoverAnimeBinding
import com.example.animeapp.ui.screens.discover.adapters.DiscoverAnimeListAdapter
import com.example.animeapp.usecase.GetSavedAnimeUseCase
import com.example.animeapp.utils.collectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverAnimeFragment : Fragment() {

    private var _binding: FragmentDiscoverAnimeBinding? = null
    private val binding: FragmentDiscoverAnimeBinding get() = _binding!!
    private val discoverAnimeViewModel: DiscoverAnimeViewModel by viewModels()
    private val animeListAdapter = DiscoverAnimeListAdapter() {id->
        findNavController().navigate(
            DiscoverAnimeFragmentDirections.actionDiscoverAnimeFragmentToDetailAnimeFragment(id)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeViews()
    }

    private fun subscribeViews() {
        discoverAnimeViewModel.mangaStateFlow.collectOnStarted(viewLifecycleOwner){
            animeListAdapter.submitData(it)
        }
    }

    private fun initViews() {
        binding.animeListRecyclerView.adapter = animeListAdapter
        binding.favouritesButton.setOnClickListener {
            findNavController().navigate(
                DiscoverAnimeFragmentDirections.actionDiscoverAnimeFragmentToSavedAnimeFragment(
                    GetSavedAnimeUseCase.SavedCommands.FAVOURITES
                )
            )
        }
        binding.toWatchButton.setOnClickListener {
            findNavController().navigate(
                DiscoverAnimeFragmentDirections.actionDiscoverAnimeFragmentToSavedAnimeFragment(
                    GetSavedAnimeUseCase.SavedCommands.TO_WATCH
                )
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}