package com.abhi.workdoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.abhi.workdoneapp.ui.AppNavHost
import com.abhi.workdoneapp.ui.screens.TaskCard
import com.abhi.workdoneapp.workdoneUI.AppTheme
import com.abhi.workdoneapp.workdoneUI.WorkDoneApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WorkDoneApp {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)){
                    AppNavHost()
                }
            }
        }
    }
}


