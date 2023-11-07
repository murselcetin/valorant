package com.morpion.valorant.presentation.nav

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Agents : Screen("agents_screen")
    object Maps : Screen("maps_screen")
    object Weapons : Screen("weapons_screen")
}