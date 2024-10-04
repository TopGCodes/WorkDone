package com.abhi.workdoneapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abhi.workdoneapp.R
import com.abhi.workdoneapp.utils.borderStroke
import com.abhi.workdoneapp.utils.shapeRadius
import com.abhi.workdoneapp.workdoneUI.AppTheme

@Composable
fun TasksScreen(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    onAddtaskClick: () -> Unit = {},
    ontaskClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
) {
    Scaffold(
        modifier
            .fillMaxSize(),
        containerColor = Color.Black,
        topBar = { ToolBar(onSearchClick, modifier) }
    ) { innerpadding ->
        Column(
            modifier = modifier
                .padding(innerpadding)
                .background(AppTheme.colors.mainbackground)
        ) {
            HeaderView()
        }
    }
}

@Preview
@Composable
fun HeaderView(modifier: Modifier = Modifier, onAddtaskClick: () -> Unit = {}) {
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

@Preview
@Composable
fun TaskCard(
    onCardClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(250.dp)
            .graphicsLayer(rotationZ = -3f)
            .padding(all = 10.dp)
    ) {
        Text(
            text = "Task Title",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.tasktitlefont)),
            modifier = modifier.padding(all = 5.dp),
            maxLines = 2
        )

        Text(
            text = "Task De afhiadhfiodso sod fjoidsuofuodufods ufosd iofuosdiufio dsuf oisdu fouad oifuaoiu foiad fioscription",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.taskdescfont)),
            modifier = modifier.padding(all = 5.dp),
        )

    }


}

