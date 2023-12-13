package com.bagusmerta.taskk.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.presentation.designsystem.component.TaskkDialog
import com.bagusmerta.taskk.presentation.screen.setting.ui.SettingScreen
import com.bagusmerta.taskk.presentation.screen.setting.ui.SettingThemeOptionScreen
import com.bagusmerta.taskk.presentation.screen.setting.ui.SettingViewModel
import com.bagusmerta.taskk.utils.wrapper.BottomSheetConfiguration
import com.bagusmerta.taskk.utils.wrapper.DefaultBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.SettingNavHost(
    navController: NavController,
    bottomSheetConfig: MutableState<BottomSheetConfiguration>
) {
    navigation(
        startDestination = SettingsFlow.SettingScreen.route,
        route = SettingsFlow.Root.route
    ) {
        composable(
            route = SettingsFlow.SettingScreen.route
        ) {
            val viewModel = hiltViewModel<SettingViewModel>()
            val showDialog = remember { mutableStateOf(false) }

            // Dialog section
            if(showDialog.value){
                TaskkDialog(
                    headerValue = stringResource(R.string.dialog_header),
                    description = stringResource(R.string.dialog_description),
                    setShowDialog = { showDialog.value = it }
                )
            }

            SettingScreen(
                viewModel = viewModel,
                onClickBack = { navController.navigateUp() },
                onClickChangeTheme = { navController.navigate(SettingsFlow.ChangeThemeScreen.route) },
                onClickInfo = { showDialog.value = true }
            )
        }

        bottomSheet(SettingsFlow.ChangeThemeScreen.route) {
            val viewModel = if(navController.previousBackStackEntry != null){
                hiltViewModel<SettingViewModel>(
                    navController.previousBackStackEntry!!
                )
            } else {
                hiltViewModel()
            }

            bottomSheetConfig.value = DefaultBottomSheet
            SettingThemeOptionScreen(
                viewModel = viewModel,
                onItemClick = { navController.navigateUp() }
            )
        }
    }
}