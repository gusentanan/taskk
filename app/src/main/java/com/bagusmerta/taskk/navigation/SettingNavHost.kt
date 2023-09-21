package com.bagusmerta.taskk.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.SettingNavHost(
    navController: NavController
){
    navigation(
        startDestination = SettingFlow.SettingScreen.route,
        route = SettingFlow.Root.route
    ){
        composable(
            route =  SettingFlow.SettingScreen.route
        ){
            val viewModel = hiltViewModel<SettingViewModel>()
        }
    }
}