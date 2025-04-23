package com.example.animeapp.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil3.load
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentDetailAnimeBinding
import com.example.animeapp.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailAnimeFragment : Fragment() {

    private var _binding: FragmentDetailAnimeBinding? = null

    private val binding: FragmentDetailAnimeBinding get() = _binding!!
    private val detailAnimeViewModel: DetailAnimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeUI()
    }

    private fun initViews() {
        binding.animeDetailSaveButton.setOnClickListener {
            detailAnimeViewModel.onFavButtonClicked()
        }

        binding.animeDetailToWatchButton.setOnClickListener {
            detailAnimeViewModel.onToWatchButtonClicked()
        }
    }

    private fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            detailAnimeViewModel.animeDetails.collectLatest { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        binding.animeDetailLoadingIndicator.visibility = View.VISIBLE
                        binding.contentLayout.visibility = View.GONE
                    }

                    is UiState.Result -> {
                        binding.animeDetailLoadingIndicator.visibility = View.GONE
                        binding.contentLayout.visibility = View.VISIBLE
                        binding.animeDetailPoster.load(uiState.result.images.jpg.largeImageUrl)
                        binding.animeDetailTitleTv.text = uiState.result.title
                        binding.animeDetailSynopsisTv.text = uiState.result.synopsis
                        binding.animeDetailGenresTv.text = uiState.result.genre

                        if(uiState.result.isInWatch) binding.toWatchIconIv.setColorFilter(R.color.red)
                        else binding.toWatchIconIv.setColorFilter(R.color.black)

                        if(uiState.result.isFavoured) binding.saveIconIv.setColorFilter(R.color.red)
                        else binding.saveIconIv.setColorFilter(R.color.black)
                    }

                    is UiState.Error -> println("Error ")
                }
            }
        }
    }
}