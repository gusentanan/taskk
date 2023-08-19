package com.bagusmerta.taskk.navigation.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.bagusmerta.taskk.navigation.HomeFlow
import com.bagusmerta.taskk.navigation.MainFlow
import com.bagusmerta.taskk.utils.vmutils.HandleEffect

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel
) {
    HandleEffect(viewModel = viewModel){
        when(it){
            SplashScreenEffect.NavigateToMain -> {
                navController.navigate(HomeFlow.Root.route){
                    popUpTo(MainFlow.Root.route){
                        inclusive = true
                    }
                }
            }
        }
    }
}