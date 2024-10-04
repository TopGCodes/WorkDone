package com.abhi.workdoneapp.ui.screens.AddTask

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abhi.workdoneapp.R
import com.abhi.workdoneapp.utils.chipBorderStroke
import com.abhi.workdoneapp.utils.chipCorers
import com.abhi.workdoneapp.workdoneUI.AppTheme

@Composable
@Preview
fun AddTaskScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier,
    onSaveClick : () -> Unit = {}
){
    Scaffold(modifier = modifier,
        containerColor = AppTheme.colors.mainbackground,
        floatingActionButton = {  DynamicSaveButton(onSaveClick) }
        ) { innerpadding ->

        AddTasksContent(

        )
    }
}

@Composable
fun AddTasksContent(

) {

}

@Preview
@Composable
fun DynamicSaveButton(onSaveClick: () -> Unit = {}){

}

@Preview
@Composable
fun WorkTypeChip(typename : String = "All", onTypeClick : () -> Unit = {}, modifier: Modifier = Modifier){

    Card(modifier = modifier
        .wrapContentSize()
        .border(chipBorderStroke, chipCorers),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.mainbackground)
        ) {
            Text(text = "#Personal",
                color = AppTheme.colors.taskCard,
                fontSize = 15.sp,
                modifier = modifier
                    .wrapContentSize()
                    .padding(all = 10.dp),
                fontFamily = FontFamily(Font(R.font.tasktitlefont))
                )
    }
}
