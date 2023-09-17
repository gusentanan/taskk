package com.bagusmerta.taskk.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.google.accompanist.navigation.material.bottomSheet


fun NavGraphBuilder.DetailNavHost(
    navController: NavController
){
    navigation(
        startDestination = DetailFlow.DetailScreen.route,
        route = DetailFlow.Root.route
    ){
        composable(
            route = DetailFlow.DetailScreen.route,
            arguments = DetailFlow.DetailScreen.arguments
        ){
            //TODO: add detail screen composable here!
            val viewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(
                viewModel = viewModel,
                onBackPress = { navController.navigateUp() },
                onClickTaskkTitle = { },
                onClickDueDate = { },
                onClickTaskkPriority = { },
                onClickTaskkCategory = { },
                onClickTaskkStatus = { },
                onClickTaskkDelete = { }
            )

            //TODO: add bottomSheet as an input for every item in detail feature
        }
    }
}