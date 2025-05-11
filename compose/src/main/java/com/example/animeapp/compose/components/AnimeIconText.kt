package com.example.animeapp.compose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.compose.R
import com.example.animeapp.compose.theme.AnimeAppTheme

@Composable
fun AnimeIconText(
    iconPainter: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(
            painter = iconPainter,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(14.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimeIconTextPreview() {
    AnimeAppTheme {
        AnimeIconText(painterResource(R.drawable.ic_star), "English")
    }
}