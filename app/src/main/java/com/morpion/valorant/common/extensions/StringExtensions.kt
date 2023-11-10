package com.morpion.valorant.common.extensions

import androidx.compose.ui.graphics.Color

fun String.stringToColorHex() = Color(android.graphics.Color.parseColor("#$this"))