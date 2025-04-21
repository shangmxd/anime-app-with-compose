package com.example.animeapp.ui.screens.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentDiscoverAnimeBinding
import com.example.animeapp.databinding.FragmentSearchAnimeBinding
import com.example.animeapp.ui.screens.discover.DiscoverAnimeFragmentDirections
import com.example.animeapp.ui.screens.search.adapters.SearchAnimeRecyclerViewAdapter
import com.example.animeapp.utils.collectOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAnimeFragment : Fragment() {

    private var _binding: FragmentSearchAnimeBinding? = null
    private val binding: FragmentSearchAnimeBinding get() = _binding!!
    private val searchAnimeViewModel:SearchAnimeViewModel by viewModels()
    private val searchAnimeRecyclerViewAdapter = SearchAnimeRecyclerViewAdapter() {id ->
        findNavController().navigate(
            DiscoverAnimeFragmentDirections.actionDiscoverAnimeFragmentToDetailAnimeFragment(id)
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchAnimeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchedAnimeOptionsRv.adapter = searchAnimeRecyclerViewAdapter

        binding.searchAnimeTextField.doOnTextChanged { text, _, _, _ ->
            text?.let {
                searchAnimeViewModel.onSearchQueryChanged(it)
            }
        }

        searchAnimeViewModel.searchedAnime.collectOnStarted(viewLifecycleOwner) {
            binding.searchedAnimeOptionsRv.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding.errorMessageTv.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            searchAnimeRecyclerViewAdapter.submitList(it)
        }
    }
}