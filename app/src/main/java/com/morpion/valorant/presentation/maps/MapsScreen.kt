package com.morpion.valorant.presentation.maps

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.morpion.valorant.R
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.White

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapsScreen(
    viewModel: MapsViewModel = hiltViewModel(),
) {
    //map picker state
    val pickerState = rememberPagerState(Int.MAX_VALUE / 2)

    //map viewer state
    val mapState = rememberPagerState(Int.MAX_VALUE / 2)

    val state = viewModel.state.value

    var isPlaying by remember {
        mutableStateOf(true)
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bg))
    val animationState by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        restartOnPlay = true
    )

    LaunchedEffect(key1 = pickerState.currentPage) {
        if (pickerState.currentPage != mapState.currentPage) {

            //isPlaying is restarting the lottie animation
            isPlaying = false

            //changing map picker will trigger change the map view state
            mapState.animateScrollToPage(pickerState.currentPage)
            isPlaying = true
        }
    }

    LaunchedEffect(key1 = mapState.currentPage) {
        if (mapState.currentPage != pickerState.currentPage) {

            //isPlaying is restarting the lottie animation
            isPlaying = false

            //changing map view will trigger change the picker view state
            pickerState.animateScrollToPage(mapState.currentPage)
            isPlaying = true
        }
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {

            //Background gradient
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                LightRed,
                                LightBlack
                            )
                        )
                    )
            )

            //show lottie animation
            LottieAnimation(
                modifier = Modifier.alpha(alpha = 0.4f),
                composition = composition,
                progress = { animationState },
                contentScale = ContentScale.Fit
            )
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (state.error.isNotBlank()) {
            Log.e("TAG", "AgentsScreen: ${state.error} ",)
        }

        if (!state.isLoading && state.maps.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                MapPager(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    listState = mapState,
                    items = state.maps
                )
                Box(modifier = Modifier.height(16.dp))
                WheelPicker(
                    items = state.maps,
                    listState = pickerState
                )
            }

        }
    }
}
