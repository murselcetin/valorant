package com.morpion.valorant.presentation.theme

import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.morpion.valorant.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val titleWhite = TextStyle(
    fontFamily = FontFamily(Font(R.font.agentfont)),
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp,
    color = White
)

val titleBlack = TextStyle(
    fontFamily = FontFamily(Font(R.font.agentfont)),
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp,
    color = Black
)

val smallRed = TextStyle(
    fontFamily = FontFamily(Font(R.font.agentfont)),
    fontWeight = FontWeight.ExtraBold,
    fontSize = 10.sp,
    color = LightRed
)

val normalWhite = TextStyle(
    fontFamily = FontFamily(Font(R.font.agentfont)),
    fontWeight = FontWeight.ExtraBold,
    fontSize = 15.sp,
    color = White
)

val normalRed = TextStyle(
    fontFamily = FontFamily(Font(R.font.agentfont)),
    fontWeight = FontWeight.ExtraBold,
    fontSize = 15.sp,
    color = LightRed
)

