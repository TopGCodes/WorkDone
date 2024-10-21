package com.abhi.workdoneapp.ui.screens.Tasks

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abhi.workdoneapp.R
import com.abhi.workdoneapp.data.Task
import com.abhi.workdoneapp.utils.borderStroke
import com.abhi.workdoneapp.utils.shapeRadius
import com.abhi.workdoneapp.workdoneUI.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    onAddtaskClick: () -> Unit = {},
    ontaskClick: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
    viewModel: TasksViewModel = hiltViewModel(),
    pullToRefreshState: PullToRefreshState = rememberPullToRefreshState()
) {
    Scaffold(
        modifier
            .fillMaxSize(),
        containerColor = Color.Black,
        topBar = { ToolBar(onSearchClick, modifier) }
    ) { innerpadding ->

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        Log.d("asd", "uistate - $uiState")

        Column(
            modifier = modifier
                .padding(innerpadding)
                .fillMaxSize()
        ) {
            HeaderView(modifier, onAddtaskClick)
            PullToRefreshBox(
                isRefreshing = uiState.isLoading,
                onRefresh = { viewModel.startShowingLoading() },
                state = pullToRefreshState,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                TasksContent(
                    modifier = modifier.fillMaxSize(),
                    tasks = uiState.tasks,
                    ontaskClick = ontaskClick,
                    showDeleteButton = uiState.isLongClicked,
                    onDeleteClick = { id ->
                        viewModel.deleteTaskById(id)
                    }
                )
            }


        }
    }
}

@Composable
fun TasksContent(
    modifier: Modifier,
    tasks: List<Task>,
    showDeleteButton: Boolean,
    ontaskClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    val cells: GridCells = GridCells.Fixed(2)
    LazyVerticalGrid(cells, modifier) {
        items(tasks, key = { task -> task.id }) { task ->
            TaskCard(
                title = task.title,
                description = task.description,
                id = task.id,
                onTaskClick = ontaskClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun HeaderView(modifier: Modifier = Modifier, onAddtaskClick: () -> Unit) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Your Tasks",
            fontSize = 30.sp,
            color = AppTheme.colors.taskCard,
            fontFamily = FontFamily(Font(R.font.mainfont))
        )
        FloatingActionButton(
            onClick = { onAddtaskClick.invoke() },
            containerColor = AppTheme.colors.mainbackground,
            contentColor = Color.White,
            modifier = modifier
                .padding(all = 1.dp)
                .border(borderStroke, shape = shapeRadius)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ToolBar(onSearchClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Welcome",
            color = AppTheme.colors.lightColor,
            modifier = modifier,
            fontSize = 22.sp
        )
        Image(painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            modifier = modifier
                .clickable { onSearchClick.invoke() }
                .padding(end = 10.dp)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TaskCard(
    title: String = "",
    description: String = "",
    id: String = "",
    onTaskClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showDeleteButton by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .width(200.dp)
            .height(250.dp)
            .graphicsLayer(rotationZ = -3f)
            .padding(all = 10.dp)
            .combinedClickable(
                onClick = {
                    showDeleteButton = false
                    onTaskClick(id)
                },
                onLongClick = { showDeleteButton = true }
            )
    ) {
        if (showDeleteButton) {
            Box(
                modifier = modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
                , contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "delete",
                    modifier = modifier.clickable { onDeleteClick(id) }
                )
            }
        }
        Text(
            text = title,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.tasktitlefont)),
            modifier = modifier.padding(all = 5.dp),
            maxLines = 2
        )
        Text(
            text = description,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.taskdescfont)),
            modifier = modifier.padding(all = 5.dp),
        )
    }
}

