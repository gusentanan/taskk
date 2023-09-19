package com.bagusmerta.taskk.navigation

import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
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
        val arguments = listOf(
            navArgument(ARG_TASKK_ID){
                defaultValue = ""
            },
            navArgument(ARG_LIST_ID){
                defaultValue = "1"
            }
        )

        val route = "$name?$ARG_TASKK_ID={$ARG_TASKK_ID}&$ARG_LIST_ID={$ARG_LIST_ID}"

        val deepLinks = listOf(navDeepLink { uriPattern = "$BASE_DEEPLINK/$route" })

        fun deeplink(taskId: String, listId: String): String {
            return "$BASE_DEEPLINK/$name?$ARG_TASKK_ID=${taskId}&$ARG_LIST_ID=${listId}"
        }
    }

    object EditTaskkNote: DetailFlow("edit-note-screen"){
        val route = name
    }

    object EditTaskkTitle: DetailFlow("edit-title-screen"){
        val route = name
    }

    object PickTaskkCategory: DetailFlow("edit-category-screen"){
        val route = name
    }

    object PickTaskkPriority: DetailFlow("edit-priority-screen"){
        val route = name
    }

}


const val BASE_DEEPLINK = "taskk://com.bagusmerta"
const val ARG_LIST_ID = "listId"
const val ARG_TASKK_ID = "taskkId"