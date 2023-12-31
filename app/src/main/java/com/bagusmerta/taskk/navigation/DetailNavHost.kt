package com.bagusmerta.taskk.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet.DetailTaskkCategoryScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet.DetailTaskkNoteScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet.DetailTaskkPriorityScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet.DetailTaskkRepeatableScreen
import com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet.DetailTaskkTitleScreen
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
            val viewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(
                viewModel = viewModel,
                onBackPress = { navController.navigateUp() },
                onRefreshScreen = { taskkId, listId ->
                    navController.navigate(DetailFlow.Root.route(taskkId, listId)){
                        popUpTo(HomeFlow.HomeScreen.route)
                    }
                },
                showCreateTaskkName = { navController.navigate(DetailFlow.EditTaskkTitle.route) },
                onClickTaskkTitle = { navController.navigate(DetailFlow.EditTaskkTitle.route) },
                onClickTaskkPriority = { navController.navigate(DetailFlow.PickTaskkPriority.route) },
                onClickTaskkCategory = { navController.navigate(DetailFlow.PickTaskkCategory.route) },
                onClickTaskkNote = { navController.navigate(DetailFlow.EditTaskkNote.route) },
                onClickTaskkDelete = { navController.navigateUp() },
                onClosePage = { navController.navigateUp() },
                onClickTaskkRepeatable = { navController.navigate(DetailFlow.PickTaskkRepeatable.route) }
            )
        }

        bottomSheet(DetailFlow.PickTaskkRepeatable.route) {
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<DetailViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }

            bottomSheetConfig.value = DefaultBottomSheet
            DetailTaskkRepeatableScreen(
                viewModel = viewModel,
                onItemClick = { navController.navigateUp() }
            )
        }

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
                onClickSave = { navController.navigateUp() }
            )
        }

        bottomSheet(DetailFlow.EditTaskkTitle.route) {
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<DetailViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }

            bottomSheetConfig.value = DefaultBottomSheet
            DetailTaskkTitleScreen(
                viewModel = viewModel ,
                onCancelClick = { navController.navigateUp() },
                onSaveClick = { navController.navigateUp() }
            )
        }

        bottomSheet(DetailFlow.PickTaskkCategory.route){
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<DetailViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }

            bottomSheetConfig.value = DefaultBottomSheet
            DetailTaskkCategoryScreen(
                viewModel = viewModel,
                onItemClick = { navController.navigateUp() }
            )
        }

        bottomSheet(DetailFlow.PickTaskkPriority.route){
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<DetailViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }

            bottomSheetConfig.value = DefaultBottomSheet
            DetailTaskkPriorityScreen(
                viewModel = viewModel,
                onItemClick = { navController.navigateUp() }
            )
        }
    }
}