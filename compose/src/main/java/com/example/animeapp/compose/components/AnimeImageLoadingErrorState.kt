package com.example.animeapp.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.animeapp.compose.R
import com.example.animeapp.compose.theme.AnimeAppTheme

@Composable
fun ImageLoadingErrorState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_cross),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "Image could not be loaded",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ImageLoadingErrorStatePreview() {
    AnimeAppTheme {
        ImageLoadingErrorState()
    }
}
