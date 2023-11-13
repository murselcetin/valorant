package com.morpion.valorant.presentation.agents

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.morpion.valorant.common.extensions.stringToColorHex
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.presentation.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import java.lang.reflect.Type

@Composable
fun AgentsItem(agent: AgentModel) {
    val configuration = LocalConfiguration.current
    val agentImageHeight = (configuration.screenHeightDp / 4f).dp
    val roleImageHeight = ((configuration.screenHeightDp / 4f) / 6f).dp

    Card(
        modifier = Modifier.padding(12.dp),
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
                imageModel = agent.displayIcon,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(),
                modifier = Modifier.size(agentImageHeight)
            )

            GlideImage(
                imageModel = agent.role?.displayIcon,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(),
                modifier = Modifier.size(roleImageHeight).align(Alignment.TopEnd).padding(end = 10.dp, top = 10.dp)
            )

            Text(
                text = agent.displayName,
                style = agentText,
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