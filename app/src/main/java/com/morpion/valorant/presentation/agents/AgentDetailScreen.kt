package com.morpion.valorant.presentation.agents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morpion.valorant.R
import com.morpion.valorant.presentation.components.TabLayout
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.presentation.components.AbilitiesPager
import com.morpion.valorant.presentation.theme.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentDetailScreen(
    onDismissRequest: () -> Unit,
    agent: AgentModel
) {
    val configuration = LocalConfiguration.current

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        modifier = Modifier.padding(top = (configuration.screenHeightDp / 24f).dp),
        sheetState = bottomSheetState,
        onDismissRequest = { onDismissRequest() },
        dragHandle = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDefaults.DragHandle(color = LightRed)
                Text(text = agent.displayName ?: "", style = titleWhite)
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = LightRed, thickness = 1.dp)
            }
        }
    ) {
        AgentDetailContent(agent = agent)
    }
}

@Composable
fun AgentDetailContent(
    agent: AgentModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()).background(LightBlack),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val configuration = LocalConfiguration.current
        agent.let {
            Box(
                modifier = Modifier
                    .background(
                        color = Black,
                        shape = RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .padding(24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    modifier = Modifier.size((configuration.screenHeightDp / 3f).dp),
                    contentScale = ContentScale.Fit,
                    imageModel = it.background,
                    circularReveal = CircularReveal(),
                    alpha = 0.2f
                )
                GlideImage(
                    modifier = Modifier.size((configuration.screenHeightDp / 3f).dp),
                    contentScale = ContentScale.Fit,
                    imageModel = it.fullPortrait,
                    circularReveal = CircularReveal(),
                    contentDescription = "Ajan"
                )

                GlideImage(
                    imageModel = agent.role?.displayIcon,
                    contentScale = ContentScale.Fit,
                    circularReveal = CircularReveal(),
                    modifier = Modifier
                        .size(((configuration.screenHeightDp / 4f) / 8f).dp)
                        .align(Alignment.TopEnd)
                )
            }

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = stringResource(R.string.description),
                style = titleWhite
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp
                ),
                text = it.description ?: "",
                textAlign = TextAlign.Center,
                style = smallRed
            )

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = stringResource(R.string.abilities),
                style = titleWhite
            )

            Spacer(modifier = Modifier.size(8.dp))

            AbilitiesPager(it.abilities ?: emptyList())

            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}