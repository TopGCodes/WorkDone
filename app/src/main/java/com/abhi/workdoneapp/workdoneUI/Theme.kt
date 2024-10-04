package com.abhi.workdoneapp.workdoneUI

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import com.abhi.workdoneapp.R

//Colors
data class WorkDoneThemeColors(
    val mainbackground: Color,
    val taskCard: Color,
    val tasksHeader: Color,
    val workTypeChip: Color,
    val taskDescription: Color,
    val lightColor : Color
)

val localappColors = staticCompositionLocalOf {
    WorkDoneThemeColors(
        mainbackground = mainBackground,
        taskCard = taskcard,
        tasksHeader = taskheader,
        workTypeChip = chipColor,
        taskDescription = taskDesc,
        lightColor = lightcolor
    )
}

val appColors = WorkDoneThemeColors(
    mainbackground = mainBackground,
    taskCard = taskcard,
    tasksHeader = taskheader,
    workTypeChip = chipColor,
    taskDescription = taskDesc,
    lightColor = lightcolor
)


//TypoGraphy
data class WorkDoneThemeTypography(
    val headerTitle: TextStyle,
    val chipsFont: TextStyle,
    val taskTitle: TextStyle,
    val taskDesc: TextStyle,
)

val localappTypography = staticCompositionLocalOf {
    WorkDoneThemeTypography(
        headerTitle = TextStyle.Default,
        chipsFont = TextStyle.Default,
        taskTitle = TextStyle.Default,
        taskDesc = TextStyle.Default,
    )
}
val appTypography = WorkDoneThemeTypography(
    headerTitle = TextStyle.Default,
    chipsFont = TextStyle.Default,
    taskTitle = TextStyle.Default,
    taskDesc = TextStyle.Default,
)

// Shapes
data class WorkDoneThemeShapes(
    val chipButton: Shape,
    val taskCard: Shape
)

//Sizes
data class WorkDoneThemeSize(
    val large: Dp,
    val medium: Dp,
    val small: Dp
)

@Composable
fun WorkDoneApp(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    if (darkTheme) appColors else appColors
    CompositionLocalProvider(
        localappColors provides appColors,
        localappTypography provides appTypography,
        content = content
    )
}


object AppTheme{
    val colors : WorkDoneThemeColors
        @Composable get() =  localappColors.current

    val typography: WorkDoneThemeTypography
        @Composable get() = localappTypography.current

}

