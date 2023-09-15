package com.bagusmerta.taskk.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


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
        }
    }
}