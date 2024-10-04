package com.abhi.workdoneapp.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhi.workdoneapp.base.Route
import com.abhi.workdoneapp.ui.screens.TasksScreen
import com.abhi.workdoneapp.workdoneUI.AppTheme

@Composable
fun AppNavHost(navHostController: NavHostController = rememberNavController()){


    NavHost(navController = navHostController, startDestination = Route.TaskScreen){
        composable<Route.TaskScreen> {
            TasksScreen(navHostController = navHostController,
                modifier = Modifier,
                onAddtaskClick = {},
                ontaskClick = {},
                onSearchClick = {}
                )
        }
        composable<Route.Search> {  }

        composable<Route.TaskCreateorUpdateScreen> {  }

    }
}