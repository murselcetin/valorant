package com.morpion.valorant.presentation.components

import androidx.compose.runtime.Composable
import com.morpion.valorant.data.remote.response.AbilitiesData

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morpion.valorant.presentation.theme.Black
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.smallRed

@Composable
fun AbilitiesPager(abilities: List<AbilitiesData>) {

    val currentSelectedAbility = remember { mutableStateOf<AbilitiesData?>(value = null) }

    Surface(modifier = Modifier.fillMaxWidth(), color = Black) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            LazyRow(modifier = Modifier.padding(5.dp)) {
                items(items = abilities, itemContent = { ability ->
                    AbilityItem(
                        ability = ability,
                        isCurrentlySelected = currentSelectedAbility.value == ability,
                        onAbilityClick = {
                            when (currentSelectedAbility.value) {
                                null -> currentSelectedAbility.value = ability
                                ability -> currentSelectedAbility.value = null
                                else -> currentSelectedAbility.apply {
                                    value = null
                                    value = ability
                                }
                            }
                        }
                    )
                })
            }

            AnimatedVisibility(
                visible = currentSelectedAbility.value != null,
                enter = slideInHorizontally(),
            ) {
                Surface(modifier = Modifier.fillMaxWidth().background(LightBlack)) {
                    Column(
                        modifier = Modifier.background(
                            Black
                        )
                    ) {
                        Spacer(modifier = Modifier.size(8.dp))
                        currentSelectedAbility.value?.description?.let {
                            Text(
                                modifier = Modifier.padding(24.dp),
                                text = it,
                                textAlign = TextAlign.Center,
                                style = smallRed
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}