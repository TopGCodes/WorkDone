package com.abhi.workdoneapp.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.workdoneapp.workdoneUI.chipColor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed


val borderStroke = BorderStroke(1.dp, Color.White)
val shapeRadius =  RoundedCornerShape(10.dp)

val chipBorderStroke = BorderStroke(1.dp, chipColor)
val chipCorers = RoundedCornerShape(8.dp)

val WhileUISubscribed : SharingStarted = SharingStarted.WhileSubscribed(5000)
