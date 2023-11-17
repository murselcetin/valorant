package com.morpion.valorant.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.morpion.valorant.data.remote.response.AbilitiesData
import com.morpion.valorant.presentation.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AbilityItem(
    modifier: Modifier = Modifier,
    isCurrentlySelected: Boolean = false,
    ability: AbilitiesData,
    onAbilityClick: (ability: AbilitiesData) -> Unit
) {
    val configuration = LocalConfiguration.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onAbilityClick(ability)
            }
    ) {
        Card(border = if (isCurrentlySelected) BorderStroke(1.dp, LightRed) else BorderStroke(0.dp, White),
            backgroundColor = LightBlack,
            shape = RoundedCornerShape(24.dp),
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                GlideImage(
                    imageModel = ability.displayIcon,
                    contentScale = ContentScale.Fit,
                    contentDescription = "Ability Item Icon",
                    modifier = Modifier.size((configuration.screenWidthDp / 7f).dp),
                    circularReveal = CircularReveal(),
                )
            }
        }
    }
}