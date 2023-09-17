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

sealed class DetailFlow(val name: String){
    object Root: DetailFlow("detail-root"){
        val route = "$name?$ARG_TASKK_ID={$ARG_TASKK_ID}&$ARG_LIST_ID={$ARG_LIST_ID}"

        fun route(taskId: String, listId: String): String {
            return "$name?$ARG_TASKK_ID=${taskId}&$ARG_LIST_ID=${listId}"
        }
    }

    object DetailScreen: DetailFlow("detail-screen"){
        val route = "$name?$ARG_TASKK_ID={$ARG_TASKK_ID}&$ARG_LIST_ID={$ARG_LIST_ID}"
        val arguments = listOf(
            navArgument(ARG_TASKK_ID){
                defaultValue = ""
            },
            navArgument(ARG_LIST_ID){
                defaultValue = "1"
            }
        )
    }

}

const val ARG_LIST_ID = "listId"
const val ARG_TASKK_ID = "taskkId"