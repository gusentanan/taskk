package com.bagusmerta.taskk.navigation

sealed class MainFlow(val name: String){
    object Root: MainFlow("main-root"){
        val route = name
    }

    object RootEmpty: MainFlow("root-empty"){
        val route = name
    }
}

sealed class HomeFlow(val name: String){
    object Root: MainFlow("home-root"){
        val route = name
    }

    object DetailScreen: HomeFlow("detail-screen"){
        val route = name
    }
}