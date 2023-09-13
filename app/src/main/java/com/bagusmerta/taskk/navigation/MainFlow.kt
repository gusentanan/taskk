package com.bagusmerta.taskk.navigation

import androidx.navigation.navArgument
import com.bagusmerta.taskk.utils.themes.ThemeSerializer.defaultValue

sealed class MainFlow(val name: String){
    object Root: MainFlow("main-root"){
        val route = name
    }

    object RootEmpty: MainFlow("root-empty"){
        val route = name
    }
}

sealed class HomeFlow(val name: String){
    object Root: HomeFlow("home-root"){
        val route = name
    }

    object HomeScreen: HomeFlow("home-screen"){
        val route = name
        val arguments = listOf(
            navArgument(ARG_LIST_ID) {
                // TODO: For now set 1 for default, might change later
                defaultValue = "1"
            }
        )
    }
}

const val ARG_LIST_ID = "listId"