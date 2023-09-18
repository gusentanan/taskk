package com.bagusmerta.taskk.navigation

import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bagusmerta.taskk.presentation.screen.splash.SplashScreen
import com.bagusmerta.taskk.presentation.screen.splash.SplashScreenViewModel
import com.bagusmerta.taskk.utils.wrapper.BottomSheetConfiguration
import com.bagusmerta.taskk.utils.wrapper.DefaultBottomSheet
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainNavHost() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val bottomSheetConfig = remember { mutableStateOf(DefaultBottomSheet) }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = bottomSheetConfig.value.sheetShape,
        scrimColor = if(bottomSheetConfig.value.showScrim){
            ModalBottomSheetDefaults.scrimColor
        } else {
            Color.Transparent
        }
    ) {
        MainScreen(bottomSheetNavigator = bottomSheetNavigator, bottomSheetConfig = bottomSheetConfig)
    }

}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen(
    bottomSheetNavigator: BottomSheetNavigator,
    bottomSheetConfig: MutableState<BottomSheetConfiguration>
){
    val navController = rememberNavController(bottomSheetNavigator)
    NavHost(
        navController = navController,
        startDestination = MainFlow.Root.route
    ){
        composable(route = MainFlow.Root.route){
            val viewModel = hiltViewModel<SplashScreenViewModel>()
            SplashScreen(navController, viewModel)
        }

        // TODO: add other child host here.
        HomeNavHost(navController)
        DetailNavHost(navController, bottomSheetConfig)
    }
}