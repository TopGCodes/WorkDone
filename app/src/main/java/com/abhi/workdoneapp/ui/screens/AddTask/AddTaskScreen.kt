package com.abhi.workdoneapp.ui.screens.AddTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.workdoneapp.base.ScreensNavigator
import com.abhi.workdoneapp.workdoneUI.AppTheme


@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    screensNavigator: ScreensNavigator,
    taskID : String?,
    onTaskUpdate : () -> Unit = {},
    snackbarhoststate : SnackbarHostState = SnackbarHostState(),
    viewModel: AddTasksViewModel = hiltViewModel()
) {
    Scaffold(modifier = modifier,
        containerColor = AppTheme.colors.mainbackground,
        snackbarHost = {
          SnackbarHost(hostState = snackbarhoststate)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { viewModel.saveTask()
            screensNavigator.navigateToTasksScreen()
            }) {
                Text(text = viewModel.buttonState.collectAsStateWithLifecycle().value)
            }
        }
    ) { innerpadding ->
        LaunchedEffect(key1 = taskID) {
            taskID?.let {
                viewModel.loadTask(it)
                viewModel.changeFabState()
            }
        }
        val uiState by viewModel.AddTaskUIState.collectAsStateWithLifecycle()

        AddTasksContent(
            title = uiState.title,
            description = uiState.description,
            onTitleChange = viewModel::updateTitle,
            onDescriptionChange = viewModel::updateDescription,
            modifier = modifier
        )
    }
}

@Composable
fun AddTasksContent(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        InputTexfield(
            textFieldValue = title,
            textFieldHeader = "Title",
            onValueChange = onTitleChange,
            modifier = modifier
        )
        InputTexfield(
            textFieldValue = description,
            textFieldHeader = "Description",
            onValueChange = onDescriptionChange,
            modifier = modifier
        )
    }
}


@Preview
@Composable
fun InputTexfield(
    textFieldValue: String = "",
    textFieldHeader: String = "Title",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
    Column(modifier.padding(all = 8.dp)) {
        Text(
            text = textFieldHeader,
            color = AppTheme.colors.taskCard,
            fontSize = 16.sp,
            modifier = modifier.padding(all = 10.dp)
        )
        OutlinedTextField(value = textFieldValue,
            onValueChange = {  onValueChange(it) },
            colors = colors,
            modifier= modifier.fillMaxWidth()
            )
    }
}


