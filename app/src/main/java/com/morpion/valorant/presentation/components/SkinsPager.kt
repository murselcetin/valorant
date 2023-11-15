package com.morpion.valorant.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morpion.valorant.data.remote.response.SkinsData
import com.morpion.valorant.presentation.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SkinsPager(skins: List<SkinsData>) {
    val configuration = LocalConfiguration.current
    val currentSelectedSkins = remember { mutableStateOf<SkinsData?>(value = null) }

    currentSelectedSkins.value = skins[0]

    Surface(modifier = Modifier.fillMaxWidth(), color = Black) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            LazyRow(modifier = Modifier.padding(5.dp)) {
                items(items = skins, itemContent = { skin ->
                    SkinItem(
                        skin = skin,
                        isCurrentlySelected = currentSelectedSkins.value == skin,
                        onSkinClick = {
                            when (currentSelectedSkins.value) {
                                null -> currentSelectedSkins.value = skin
                                else -> currentSelectedSkins.apply {
                                    value = null
                                    value = skin
                                }
                            }
                        }
                    )
                })
            }

            Column(Modifier.padding(bottom = 10.dp)) {
                Surface(modifier = Modifier.fillMaxWidth().background(LightBlack),) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.background(
                            Black
                        )
                    ) {
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = currentSelectedSkins.value?.displayName?:"",
                            style = normalWhite.copy(color = LightRed),
                            textAlign = TextAlign.Center
                        )
                        currentSelectedSkins.value?.displayIcon?.let {
                            GlideImage(
                                modifier = Modifier.size((configuration.screenHeightDp / 3f).dp),
                                contentScale = ContentScale.Fit,
                                imageModel = it,
                                circularReveal = CircularReveal(),
                                contentDescription = "Kostüm"
                            )
                        }
                    }
                }
            }
        }
    }
}