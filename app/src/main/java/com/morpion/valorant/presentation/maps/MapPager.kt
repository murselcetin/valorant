package com.morpion.valorant.presentation.maps

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morpion.valorant.domain.model.MapModel
import com.morpion.valorant.presentation.theme.White
import com.morpion.valorant.presentation.theme.mapText
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapPager(
    modifier: Modifier,
    listState: PagerState,
    items: List<MapModel>
) {
    val height = LocalConfiguration.current.screenHeightDp

    HorizontalPager(
        modifier = modifier,
        pageCount = Int.MAX_VALUE,
        state = listState
    ) {
        val pageOffset = (listState.currentPage - it) + listState.currentPageOffsetFraction

        val alpha by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) 0.1f else 0.3f,
            animationSpec = tween(easing = LinearOutSlowInEasing), label = "",
        )

        val boxSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
            animationSpec = tween(durationMillis = 300), label = ""
        )

        val yPosition by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) pageOffset - (height / 4) else pageOffset,
            animationSpec = tween(durationMillis = 200), label = ""
        )

        val textSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) 0.0f else 1f,
            animationSpec = tween(durationMillis = 300), label = ""
        )

        val textAlpha by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) 0.0f else 1f,
            animationSpec = tween(durationMillis = 300), label = ""
        )

        val backgroundSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) 0.8f else 1f,
            animationSpec = tween(durationMillis = 300), label = ""
        )

        val index = it % items.size

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = backgroundSize
                        scaleY = backgroundSize
                    }
                    .offset(
                        y = yPosition.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    imageModel = items[index].splash,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.alpha(alpha)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp)
            ) {
                Text(
                    text = items[index].displayName?.uppercase()?:"",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .alpha(textAlpha)
                        .graphicsLayer {
                            scaleX = textSize
                            scaleY = textSize
                        },
                    style = mapText,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = items[index].coordinates?:"",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .alpha(textAlpha)
                        .graphicsLayer {
                            scaleX = textSize
                            scaleY = textSize
                        },
                    style = mapText.copy(color = White),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .graphicsLayer {
                            scaleX = boxSize
                            scaleY = boxSize
                        },
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        imageModel = items[index].displayIcon,
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }
}
