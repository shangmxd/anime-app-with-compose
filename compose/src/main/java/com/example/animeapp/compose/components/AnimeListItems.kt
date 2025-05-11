package com.example.animeapp.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.animeapp.compose.R
import com.example.animeapp.compose.theme.AnimeAppTheme

@Composable
fun AnimeListItems(
    model: Any?,
    animeTitle: String,
    animeYear: Int,
    animeEpisodes: Int,
    animeBackground: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(15.dp)) {
        SubcomposeAsyncImage(
            model = model,
            loading = { ImageLoadingState() },
            error = { ImageLoadingErrorState() },
            contentDescription = "Anime Poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 140.dp, height = 180.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = animeTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 25.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            AnimeIconText(
                iconPainter = painterResource(R.drawable.ic_calender),
                "Year:$animeYear",
                modifier = Modifier.padding(top = 6.dp)
            )

            AnimeIconText(
                iconPainter = painterResource(R.drawable.ic_pc),
                text = "Episodes:$animeEpisodes",
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = animeBackground,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 6.dp)
                )

        }
    }
}

@Composable
private fun ImageLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Preview(showBackground = true)
@Composable
private fun AnimeListItemPreview() {
    AnimeAppTheme {
        AnimeListItems(
            null,
            "Attack on Titans",
            2022,
            788,
            "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them.\\n\\nHowever, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \\\"usual\\\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through.\\n\\nAs the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted",
            onClick = {}
        )
    }
}