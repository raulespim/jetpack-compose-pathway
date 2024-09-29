package com.raulespim.superheroes

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulespim.superheroes.data.DataSource
import com.raulespim.superheroes.model.Superhero
import com.raulespim.superheroes.ui.theme.SuperheroesTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuperheroesList(
    superheroes: List<Superhero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    val isInPreview = LocalInspectionMode.current
    val visibleState = remember {
        MutableTransitionState(isInPreview).apply { targetState = true }
    }

    AnimatedVisibility(
        visibleState = visibleState, enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ), exit = fadeOut(), modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(superheroes) { index, superhero ->
                SuperheroListItem(
                    superhero = superhero, modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                )
            }
        }
    }

}

@Composable
fun SuperheroListItem(superhero: Superhero, modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = superhero.name),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = superhero.description),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(shape = MaterialTheme.shapes.small)
            ) {
                Image(
                    painter = painterResource(id = superhero.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SuperheroPreview() {
    val superhero = Superhero(
        R.string.hero1,
        R.string.description1,
        R.drawable.android_superhero1
    )
    SuperheroesTheme {
        SuperheroListItem(superhero = superhero)
    }
}

@Preview("Heroes List")
@Composable
fun HeroesPreview() {
    SuperheroesTheme(darkTheme = false) {
        Surface (
            color = MaterialTheme.colorScheme.background
        ) {
            /* Important: It is not a good practice to access data source directly from the UI.
            In later units you will learn how to use ViewModel in such scenarios that takes the
            data source as a dependency and exposes heroes.
            */
            SuperheroesList(superheroes = DataSource.superheroes)
        }
    }
}