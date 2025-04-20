package com.example.animeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.ui.screens.discover.DiscoverAnimeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            findNavController(binding.fragmentContainerView.id).navigate(
                DiscoverAnimeFragmentDirections.actionDiscoverAnimeFragmentToSearchAnimeFragment()
            )
        }

    }
}