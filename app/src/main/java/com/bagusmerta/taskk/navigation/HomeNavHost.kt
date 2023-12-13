package com.bagusmerta.taskk.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bagusmerta.taskk.presentation.screen.taskk.ui.HomeScreen
import com.bagusmerta.taskk.presentation.screen.taskk.ui.HomeViewModel

fun NavGraphBuilder.HomeNavHost(
    navController: NavHostController,
){
    navigation(
        startDestination = HomeFlow.HomeScreen.route,
        route = HomeFlow.Root.route
    ) {
        composable(
            route = HomeFlow.HomeScreen.route,
            arguments = HomeFlow.HomeScreen.arguments
        ){
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                onAddTaskClick = { navController.navigate(DetailFlow.Root.route) },
                onTaskItemClick = {
                    taskkId, listId ->
                    navController.navigate(DetailFlow.Root.route(taskkId, listId))
                },
                onClickSettings = { navController.navigate(SettingsFlow.Root.route) },
            )
        }
    }

}