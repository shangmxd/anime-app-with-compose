package com.example.animeapp.ui.screens.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentSavedAnimeBinding
import com.example.animeapp.ui.screens.saved.adapters.SavedAnimeListAdapter
import com.example.animeapp.utils.collectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedAnimeFragment : Fragment() {

    private var _binding: FragmentSavedAnimeBinding? = null
    private val binding
        get() = _binding!!
    private val savedAnimeViewModel: SavedAnimeViewModel by viewModels()

    private val savedAnimeListAdapter = SavedAnimeListAdapter {
        findNavController().navigate(SavedAnimeFragmentDirections.actionSavedAnimeFragmentToDetailAnimeFragment(it))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSavedAnimeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeViews()

    }

    private fun initViews() {
        binding.savedAnimeRecyclerView.adapter = savedAnimeListAdapter
    }

    private fun subscribeViews() {
        savedAnimeViewModel.savedAnime.collectOnStarted(viewLifecycleOwner) {
            savedAnimeListAdapter.submitList(it)
        }
    }
}