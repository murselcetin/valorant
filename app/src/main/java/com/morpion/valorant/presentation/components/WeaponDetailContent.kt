package com.morpion.valorant.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.morpion.valorant.R
import com.morpion.valorant.data.remote.response.SkinsData
import com.morpion.valorant.domain.model.WeaponModel
import com.morpion.valorant.presentation.theme.Black
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.titleWhite
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeaponDetailContent(
    weapon: WeaponModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()).background(LightBlack),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val configuration = LocalConfiguration.current
        weapon.let {
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
                    imageModel = it.displayIcon,
                    circularReveal = CircularReveal(),
                    contentDescription = "Silah"
                )
            }

            if (!it.weaponStats?.damageRanges.isNullOrEmpty()){
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = stringResource(R.string.damage_range),
                    style = titleWhite
                )
                Spacer(modifier = Modifier.size(8.dp))
            }

            it.weaponStats?.damageRanges?.getOrNull(0)?.let { damageRange ->
                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                ) {
                    damageRange.headDamage?.div(200)?.let { it1 ->
                        CustomProgressIndicator(
                            title = stringResource(R.string.text_head),
                            progress = it1.toFloat(),
                            damage = damageRange.headDamage.toString()
                        )
                    }

                    damageRange.bodyDamage?.toFloat()?.div(200)?.let { it1 ->
                        CustomProgressIndicator(
                            title = stringResource(R.string.text_body),
                            progress = it1,
                            damage = damageRange.bodyDamage.toString()
                        )
                    }

                    damageRange.legDamage?.div(200)?.let { it1 ->
                        CustomProgressIndicator(
                            title = stringResource(R.string.text_leg),
                            progress = it1.toFloat(),
                            damage = damageRange.legDamage.toString()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = stringResource(R.string.skins),
                style = titleWhite
            )

            Spacer(modifier = Modifier.size(8.dp))

            val newSkinList = ArrayList<SkinsData>()
            it.skins?.forEach { itSkin ->
                if (!itSkin.displayIcon.isNullOrEmpty()) {
                    newSkinList.add(itSkin)
                }
            }

            SkinsPager(newSkinList)

            Spacer(modifier = Modifier.size((configuration.screenHeightDp / 10f).dp))
        }
    }
}