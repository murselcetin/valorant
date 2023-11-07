package com.morpion.valorant.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.morpion.valorant.presentation.nav.BottomNavBar
import com.morpion.valorant.presentation.nav.NavGraph
import com.morpion.valorant.presentation.nav.Screen
import com.morpion.valorant.presentation.theme.Black
import com.morpion.valorant.presentation.theme.ValorantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantTheme {
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) {
                    Screen.Splash.route -> bottomBarState.value = false
                    else -> bottomBarState.value = true
                }

                Scaffold(
                    backgroundColor = Black,
                    bottomBar = { BottomNavBar(navController, bottomBarState) }
                ) {
                    NavGraph(navController = navController, it)
                }
            }
        }
    }
}