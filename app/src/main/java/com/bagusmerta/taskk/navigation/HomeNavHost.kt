package com.bagusmerta.taskk.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bagusmerta.taskk.presentation.screen.setting.ui.SettingScreen
import com.bagusmerta.taskk.presentation.screen.setting.ui.SettingViewModel
import com.bagusmerta.taskk.presentation.screen.taskk.ui.HomeScreen
import com.bagusmerta.taskk.presentation.screen.taskk.ui.HomeViewModel
import com.bagusmerta.taskk.utils.wrapper.BottomSheetConfiguration
import com.bagusmerta.taskk.utils.wrapper.DefaultBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.HomeNavHost(
    navController: NavHostController,
    bottomSheetConfig: MutableState<BottomSheetConfiguration>
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
                onClickSettings = { navController.navigate(HomeFlow.SettingScreen.route) },
            )
        }

        bottomSheet(HomeFlow.SettingScreen.route){
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<SettingViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }
            bottomSheetConfig.value  = DefaultBottomSheet
            SettingScreen(
                viewModel = viewModel,
                onClickBack = {
                        navController.navigateUp()
                }
            )

        }
    }

}