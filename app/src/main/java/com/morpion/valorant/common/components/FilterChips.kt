package com.morpion.valorant.common.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.morpion.valorant.data.remote.response.RoleData
import com.morpion.valorant.presentation.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChips(
    roleList: List<RoleData>,
    selectedItem: (String) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val filterHeight = (configuration.screenHeightDp / 12f).dp

    val selectedItem = remember { mutableStateOf("") }

    LazyHorizontalGrid(
        modifier = Modifier
            .height(filterHeight)
            .padding(top = 10.dp, start = 20.dp),
        contentPadding = PaddingValues(2.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(roleList) { itRole ->
            FilterChip(
                modifier = Modifier.padding(end = 4.dp),
                selected = (itRole.displayName == selectedItem.value),
                onClick = {
                    selectedItem.value = itRole.displayName ?: ""
                    selectedItem(itRole.displayName ?: "")
                },
                label = {
                    Text(
                        text = itRole.displayName ?: "".uppercase(),
                        style = normalWhite,
                        fontWeight = if (itRole.displayName == selectedItem.value) FontWeight.ExtraBold else FontWeight.Normal
                    )
                },
                leadingIcon = if (itRole.displayName == selectedItem.value) {
                    {
                        GlideImage(
                            modifier = Modifier.size(AssistChipDefaults.IconSize+5.dp),
                            imageModel = itRole.displayIcon,
                            contentScale = ContentScale.Fit,
                        )
                    }
                } else {
                    {
                        GlideImage(
                            modifier = Modifier.size(AssistChipDefaults.IconSize),
                            imageModel = itRole.displayIcon,
                            contentScale = ContentScale.Fit,
                        )
                    }
                },
                colors = FilterChipDefaults.filterChipColors(
                    labelColor = White,
                    selectedLabelColor = LightRed,
                    selectedLeadingIconColor = LightRed,
                    selectedContainerColor = LightBlack.copy(alpha = 0.1f)
                ),
                border = FilterChipDefaults.filterChipBorder(
                    selectedBorderColor = LightRed,
                    selectedBorderWidth = 2.dp,
                    borderColor = White
                )
            )
        }
    }
}
