package com.raulespim.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulespim.artspace.ui.theme.ArtSpaceTheme
import com.raulespim.artspace.utils.ArtWork

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {

    var currentArtWork by rememberSaveable { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .widthIn(max = 100.dp)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(getArtWorkImage(currentArtWork)),
            contentDescription = null,
            modifier = Modifier
                .widthIn(max = 400.dp)
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                .shadow(2.dp)
                .padding(32.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        ArtWorkInfo(
            title = getArtWorkTitle(currentArtWork),
            artist = getArtWorkArtist(currentArtWork),
            yearPub = getArtWorkYearPub(currentArtWork)
        )
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
        ActionButtons(
            onPreviousClick = {
                if (currentArtWork > 1) currentArtWork--
            },
            onNextClick = {
                if (currentArtWork == 3) currentArtWork = 1
                else currentArtWork++
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}

@Composable
fun ArtWorkInfo(
    @StringRes title: Int,
    @StringRes artist: Int,
    @StringRes yearPub: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color.Gray.copy(alpha = 0.14f)
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Row {
            Text(text = stringResource(id = artist), fontWeight = FontWeight.Bold)
            Text(text = stringResource(id = yearPub), modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun ActionButtons(
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPreviousClick, modifier = Modifier.weight(1f)) {
            Text(text = stringResource(R.string.previous))
        }
        Spacer(modifier = Modifier.padding(end = 48.dp))
        Button(onClick = onNextClick, modifier = Modifier.weight(1f)) {
            Text(text = stringResource(R.string.next))
        }
    }
}

private fun getArtWorkImage(currentArtWork: Int) = when (currentArtWork) {
    ArtWork.GUADALUPE.value -> R.drawable.guadalupe
    ArtWork.THE_LAST_SUPPER.value -> R.drawable.the_last_supper
    else -> R.drawable.the_transfiguration
}

private fun getArtWorkTitle(currentArtWork: Int) = when (currentArtWork) {
    ArtWork.GUADALUPE.value -> R.string.guadalupe_title
    ArtWork.THE_LAST_SUPPER.value -> R.string.the_last_supper_title
    else -> R.string.the_transfiguration_title
}

private fun getArtWorkArtist(currentArtWork: Int) = when (currentArtWork) {
    ArtWork.GUADALUPE.value -> R.string.guadalupe_artist
    ArtWork.THE_LAST_SUPPER.value -> R.string.the_last_supper_artist
    else -> R.string.the_transfiguration_artist
}

private fun getArtWorkYearPub(currentArtWork: Int) = when (currentArtWork) {
    ArtWork.GUADALUPE.value -> R.string.guadalupe_year_publication
    ArtWork.THE_LAST_SUPPER.value -> R.string.the_last_supper_year_publication
    else -> R.string.the_transfiguration_year_publication
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArtSpaceLayout()
        }
    }
}