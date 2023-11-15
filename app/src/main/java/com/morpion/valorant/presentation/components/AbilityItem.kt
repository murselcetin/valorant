package com.morpion.valorant.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.morpion.valorant.data.remote.response.AbilitiesData
import com.morpion.valorant.presentation.theme.White
import com.morpion.valorant.presentation.theme.normalWhite
import com.morpion.valorant.presentation.theme.smallRed
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemAbility(
    modifier: Modifier = Modifier,
    isCurrentlySelected: Boolean = false,
    ability: AbilitiesData,
    onAbilityClick: (ability: AbilitiesData) -> Unit
) {
    val configuration = LocalConfiguration.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp,end = 10.dp).clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) {
            onAbilityClick(ability)
        }
    ) {
        GlideImage(
            imageModel = ability.displayIcon,
            contentDescription = "Ability Item Icon",
            modifier = Modifier
                .size((configuration.screenWidthDp / 8f).dp),
            circularReveal = CircularReveal(),
        )

        Spacer(modifier = Modifier.size(8.dp))

        if (isCurrentlySelected){
            Text(
                text = ability.displayName ?: "",
                style = smallRed
            )
        } else {
            Text(
                text = ability.displayName ?: "",
                style = smallRed.copy(color = White)
            )
        }

    }
}