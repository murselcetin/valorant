package com.morpion.valorant.presentation.maps

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morpion.valorant.domain.model.MapModel
import com.morpion.valorant.presentation.theme.Black
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.agentText
import com.morpion.valorant.presentation.theme.mapTextSmall
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WheelPicker(
    items: List<MapModel>,
    listState: PagerState
) {
    val scope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp / 3

    fun onTap(index: Int) {
        scope.launch {
            listState.animateScrollToPage(index)
        }
    }

    HorizontalPager(
        pageCount = Int.MAX_VALUE,
        state = listState,
        contentPadding = PaddingValues(horizontal = screenWidth.dp),
    ) {

        val pageOffset = (listState.currentPage - it) + listState.currentPageOffsetFraction

        val boxSize = 80f

        val size by animateFloatAsState(
            targetValue = if (pageOffset == 0f) boxSize else (boxSize / 1.2).toFloat(),
            animationSpec = tween(easing = LinearOutSlowInEasing), label = "",
        )

        val color by animateColorAsState(
            targetValue = if (pageOffset == 0f) LightRed else Black,
            animationSpec = tween(easing = LinearOutSlowInEasing), label = "",
        )

        val borderWidth by animateIntAsState(
            targetValue = if (pageOffset == 0f) 3 else 0,
            animationSpec = tween(easing = LinearOutSlowInEasing), label = "",
        )

        val index = it % items.size
        Box(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(boxSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(size.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .border(borderWidth.dp, color)
                    .clickable {
                        onTap(it)
                    },
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    imageModel = items[index].splash,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(size.dp)
                        .alpha(if (it == listState.currentPage) 1f else 0.3f)
                )

                Text(
                    text = items[index].displayName?:"",
                    style = mapTextSmall,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}