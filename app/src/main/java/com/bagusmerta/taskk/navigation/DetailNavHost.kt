package com.bagusmerta.taskk.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet.DetailTaskkNoteScreen
import com.bagusmerta.taskk.utils.wrapper.BottomSheetConfiguration
import com.bagusmerta.taskk.utils.wrapper.DefaultBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet


@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.DetailNavHost(
    navController: NavHostController,
    bottomSheetConfig: MutableState<BottomSheetConfiguration>
){
    navigation(
        startDestination = DetailFlow.DetailScreen.route,
        route = DetailFlow.Root.route
    ){
        composable(
            route = DetailFlow.DetailScreen.route,
            arguments = DetailFlow.DetailScreen.arguments,
            deepLinks = DetailFlow.DetailScreen.deepLinks
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
                onClickTaskkNote = { navController.navigate(DetailFlow.EditTaskkNote.route) },
                onClickTaskkStatus = { },
                onClickTaskkDelete = { }
            )
        }

        //TODO: BottomSheet of Edit Taskk Note
        bottomSheet(DetailFlow.EditTaskkNote.route) {
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<DetailViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }

            bottomSheetConfig.value  = DefaultBottomSheet
            DetailTaskkNoteScreen(
                viewModel = viewModel,
                onClickBack = { navController.navigateUp() }
            )
        }
    }
}