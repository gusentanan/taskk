package com.bagusmerta.taskk.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bagusmerta.taskk.navigation.splash.SplashScreen
import com.bagusmerta.taskk.navigation.splash.SplashScreenViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainFlow.Root.route
    ){
        composable(route = MainFlow.Root.route){
           val viewModel = hiltViewModel<SplashScreenViewModel>()
            SplashScreen(navController, viewModel)
        }

        // TODO: add other child host here.
    }
}