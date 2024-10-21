package com.abhi.workdoneapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abhi.workdoneapp.base.Route
import com.abhi.workdoneapp.base.ScreensNavigator
import com.abhi.workdoneapp.ui.screens.AddTask.AddTaskScreen
import com.abhi.workdoneapp.ui.screens.Tasks.TasksScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    screensNavigator: ScreensNavigator = remember(navHostController) {
        ScreensNavigator(navHostController)
    }
) {
    NavHost(navController = navHostController, startDestination = Route.TaskScreen) {
        composable<Route.TaskScreen> {
            TasksScreen(navHostController = navHostController,
                modifier = Modifier,
                onAddtaskClick = { screensNavigator.navigateToTaskCreateOrUpdateScreen(null)},
                ontaskClick = { taskId -> screensNavigator.navigateToTaskCreateOrUpdateScreen(taskId)},
                onSearchClick = {  }
            )
        }
        composable<Route.Search> { }

        composable<Route.TaskCreateorUpdateScreen> { backStackEntry ->
            val args: Route.TaskCreateorUpdateScreen = backStackEntry.toRoute()
            AddTaskScreen(
                modifier = modifier,
                screensNavigator = screensNavigator,
                taskID = args.taskID
            )
        }

    }
}