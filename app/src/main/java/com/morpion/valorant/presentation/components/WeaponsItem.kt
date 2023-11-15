package com.morpion.valorant.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.model.WeaponModel
import com.morpion.valorant.presentation.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeaponsItem(weapon: WeaponModel, onItemClick: (WeaponModel) -> Unit) {
    val configuration = LocalConfiguration.current
    val weaponImageHeight = (configuration.screenHeightDp / 4f).dp

    Card(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onItemClick(weapon) },
        elevation = 4.dp,
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            LightBlack,
                            LightRed
                        )
                    )
                )
        ) {
            GlideImage(
                imageModel = weapon.displayIcon,
                contentScale = ContentScale.Fit,
                circularReveal = CircularReveal(),
                modifier = Modifier.size(weaponImageHeight)
            )

            Text(
                text = weapon.displayName ?: "".uppercase(),
                style = titleWhite,
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Black
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}