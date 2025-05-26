package com.it.shka.searchjobapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MessageScreen(){
    Box(modifier = Modifier
        .fillMaxSize(),

        contentAlignment =Alignment.Center
    ){
        Text(
            text = "MessageScreen",
            color = Color.Black,
            fontSize = 14.sp )
    }

}