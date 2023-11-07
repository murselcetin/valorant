package com.morpion.valorant.presentation.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morpion.valorant.presentation.AgentsScreen
import com.morpion.valorant.presentation.MapsScreen
import com.morpion.valorant.presentation.SplashScreen
import com.morpion.valorant.presentation.WeaponsScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        //modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

       composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToAgents = {
                    navController.navigate(Screen.Agents.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.Agents.route) {
            AgentsScreen()
        }

        composable(route = Screen.Maps.route) {
            MapsScreen()
        }

        composable(route = Screen.Weapons.route) {
            WeaponsScreen()
        }
    }
}