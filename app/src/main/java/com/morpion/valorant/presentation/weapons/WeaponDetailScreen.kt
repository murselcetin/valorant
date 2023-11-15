package com.morpion.valorant.presentation.weapons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.morpion.valorant.domain.model.WeaponModel
import com.morpion.valorant.presentation.components.WeaponDetailContent
import com.morpion.valorant.presentation.theme.Black
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.titleWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponDetailScreen(
    onDismissRequest: () -> Unit,
    weapon: WeaponModel
) {
    val configuration = LocalConfiguration.current

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        modifier = Modifier.padding(top = (configuration.screenHeightDp / 24f).dp),
        sheetState = bottomSheetState,
        onDismissRequest = { onDismissRequest()},
        dragHandle = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDefaults.DragHandle(color = LightRed)
                Text(text = weapon.displayName ?: "".uppercase(), style = titleWhite)
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = LightRed, thickness = 1.dp)
            }
        }
    ) {
        WeaponDetailContent(weapon = weapon)
    }
}