package com.morpion.valorant.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.morpion.valorant.data.remote.response.AbilitiesData
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.normalWhite
import com.morpion.valorant.presentation.theme.smallRed
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(
    abilities: List<AbilitiesData>
) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
    ) {
        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(
                                tabPositions[pagerState.currentPage]
                            )
                            .width(0.dp)
                            .height(0.dp)
                    )
                }
            ) {
                abilities.forEachIndexed { index, ability ->
                    val color = remember {
                        Animatable(LightRed)
                    }

                    LaunchedEffect(
                        pagerState.currentPage == index
                    ) {
                        color.animateTo(if (pagerState.currentPage == index) LightRed else LightBlack)
                    }
                    Tab(
                        icon = {
                            GlideImage(
                                imageModel = ability.displayIcon,
                                circularReveal = CircularReveal(),
                                contentScale = ContentScale.Fit,
                                colorFilter = if (pagerState.currentPage == index) ColorFilter.tint(
                                    Color.White
                                )
                                else ColorFilter.tint(Color.Gray),
                                modifier = Modifier.size(32.dp)
                            )
                        },
                        selected = pagerState.currentPage == index,
                        modifier = Modifier
                            .background(color = color.value),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.size(12.dp))

            HorizontalPager(
                count = abilities.size,
                state = pagerState
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = abilities[page].displayName.orEmpty(),
                        style = normalWhite
                    )

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = abilities[page].description.orEmpty(),
                        style = smallRed,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}