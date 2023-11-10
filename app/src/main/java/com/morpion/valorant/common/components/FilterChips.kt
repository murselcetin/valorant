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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChips(
    roleList: List<String>,
    selectedRole: (String) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val filterHeight = (configuration.screenHeightDp / 12f).dp

    val selectedItem = remember { mutableStateOf("") }

    LazyHorizontalGrid(
        modifier = Modifier
            .height(filterHeight)
            .padding(top = 10.dp, start = 12.dp),
        contentPadding = PaddingValues(2.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(roleList) { itRole ->
            FilterChip(
                modifier = Modifier.padding(all = 6.dp),
                selected = (itRole == selectedItem.value),
                onClick = {
                    selectedItem.value = itRole
                    selectedRole(itRole)
                },
                label = {
                    Text(
                        text = itRole,
                        fontWeight = if (itRole == selectedItem.value) FontWeight.Medium else FontWeight.Normal
                    )
                },
                leadingIcon = if (itRole == selectedItem.value) {
                    {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            modifier = Modifier.size(
                                FilterChipDefaults.IconSize
                            ),
                            tint = LightRed
                        )
                    }
                } else {
                    null
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
