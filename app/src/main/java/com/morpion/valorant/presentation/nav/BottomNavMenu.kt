package com.morpion.valorant.presentation.nav

import com.morpion.valorant.R

sealed class BottomNavItem(
    val title: String,
    val image: Int,
    val route: String
) {
    object Agents : BottomNavItem(
        title = "Agents",
        image = R.drawable.ic_base,
        route = Screen.Agents.route
    )

    object Maps : BottomNavItem(
        title = "Maps",
        image = R.drawable.ic_base,
        route = Screen.Maps.route
    )

    object Weapons : BottomNavItem(
        title = "Weapons",
        image = R.drawable.ic_base,
        route = Screen.Weapons.route
    )
}