package com.morpion.valorant.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.morpion.valorant.data.remote.response.SkinsData
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.White
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SkinItem(
    isCurrentlySelected: Boolean = false,
    skin: SkinsData,
    onSkinClick: (skins: SkinsData) -> Unit
) {
    val configuration = LocalConfiguration.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onSkinClick(skin)
            }
    ) {
        Card(border = if (isCurrentlySelected) BorderStroke(1.dp, LightRed) else BorderStroke(0.dp, White),
            backgroundColor = LightBlack,
            shape = RoundedCornerShape(24.dp)
        ){
            if (skin.displayIcon != null) {
                GlideImage(
                    imageModel = skin.displayIcon,
                    contentScale = ContentScale.Fit,
                    contentDescription = "Skin Item Icon",
                    modifier = Modifier.size((configuration.screenWidthDp / 5f).dp),
                    circularReveal = CircularReveal(),
                )
            }
        }
    }
}