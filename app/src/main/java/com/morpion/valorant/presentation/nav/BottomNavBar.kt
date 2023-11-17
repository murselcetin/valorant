package com.morpion.valorant.presentation.nav

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.morpion.valorant.R
import com.morpion.valorant.presentation.theme.LightBlack
import com.morpion.valorant.presentation.theme.LightRed
import com.morpion.valorant.presentation.theme.White

@Composable
fun BottomNavBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val items = listOf(
        BottomNavItem(
            title = LocalContext.current.getString(R.string.agent),
            image = R.drawable.ic_agents,
            route = Screen.Agents.route
        ),
        BottomNavItem(
            title = LocalContext.current.getString(R.string.map),
            image = R.drawable.ic_map,
            route = Screen.Maps.route
        ),
        BottomNavItem(
            title = LocalContext.current.getString(R.string.weapon),
            image = R.drawable.ic_weapons,
            route = Screen.Weapons.route
        ),
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { _ ->
                    BottomNavigation(
                        backgroundColor = LightBlack
                    ) {
                        items.forEach { item ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        modifier=Modifier.padding(6.dp),
                                        painter = painterResource(id = item.image),
                                        contentDescription = stringResource(id = item.image)
                                    )
                                },
                                label = { Text(text = item.title) },
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                selectedContentColor = LightRed,
                                unselectedContentColor = White,
                            )
                        }
                    }
                }
            }
        }
    )
}