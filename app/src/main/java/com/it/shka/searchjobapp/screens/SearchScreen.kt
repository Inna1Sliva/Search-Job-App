package com.it.shka.searchjobapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.it.shka.searchjobapp.RootNavGraph
import com.it.shka.searchjobapp.model.NavSearchScreen

@Composable
fun SearchScreen(openSearchScreen:()-> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SearchScreen",
            color = Color.White,
            fontSize = 14.sp)
            Button(onClick = openSearchScreen
            ) { }

    }
}