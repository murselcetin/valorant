package com.morpion.valorant.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.morpion.valorant.R
import com.morpion.valorant.presentation.theme.*

@Composable
fun SearchView(
    searchText: String,
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
) {
    var showClearButton by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
        border = BorderStroke(1.dp, White),
        backgroundColor = LightBlack,
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                tint = LightRed
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        showClearButton = (focusState.isFocused || searchText.isNotBlank())
                    },
                value = searchText,
                onValueChange = onSearchTextChanged,
                placeholder = {
                    Text(
                        style = normalWhite,
                        text = stringResource(R.string.search),
                        modifier = Modifier.wrapContentHeight(),
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    cursorColor = LightRed
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = showClearButton,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(onClick = {
                            onClearClick()
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(R.string.close),
                                tint = LightRed
                            )
                        }
                    }
                },
                maxLines = 1,
                singleLine = true
            )
        }
    }
}