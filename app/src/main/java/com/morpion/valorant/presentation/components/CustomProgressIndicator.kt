package com.morpion.valorant.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.ui.graphics.StrokeCap
import com.morpion.valorant.presentation.theme.*

@Composable
fun CustomProgressIndicator(
    progress: Float,
    title: String,
    damage: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = normalWhite,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(4.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.height(10.dp),
            color = LightRed,
            backgroundColor = White,
            strokeCap = StrokeCap.Round
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = damage,
            style = smallRed,
            textAlign = TextAlign.Center
        )
    }
}