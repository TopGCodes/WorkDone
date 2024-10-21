package com.abhi.workdoneapp.base

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object Search : Route()

    @Serializable
    data object TaskScreen : Route()

    @Serializable
    data class TaskCreateorUpdateScreen(val taskID : String ?= null): Route()

    @Serializable
    data object SplashScreen : Route()
}


// StateHolder class for whole Navigation
class ScreensNavigator(val navController: NavController){

    // navigate to TaskCreate or taskUpdate Screen
    fun navigateToTaskCreateOrUpdateScreen(taskid : String?){
        navController.navigate(Route.TaskCreateorUpdateScreen(taskid)){

        }
    }
    fun navigateToTasksScreen(){
        navController.navigate(Route.TaskScreen){
        }
    }
}




