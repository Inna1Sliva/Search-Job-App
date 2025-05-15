package com.it.shka.searchjobapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.it.shka.searchjobapp.R
import java.nio.file.WatchEvent

@Preview(showBackground = true)
@Composable
fun VacanciesScreen(){
    var textWhere = remember { mutableStateOf( "" ) }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentSize()
            ){
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = colorResource(R.color.search_bag), shape = RoundedCornerShape(8.dp))) {
                Row {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(modifier = Modifier
                            .size(width = 25.dp, height = 25.dp),
                            painter = painterResource(R.drawable.icon_search), contentDescription = "",
                            tint = colorResource(R.color.Basic_Grey_4)
                        )
                    }
                    BasicTextField(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .width(250.dp)   ,
                        value = textWhere.value,
                        onValueChange = {textWhere.value = it},
                        decorationBox = {
                            Text("Должность, ключевые слова",
                                fontSize = 16.sp,
                                color = colorResource(R.color.search_text))
                        }
                    )
                }

            }
            Spacer(modifier = Modifier
                .width(16.dp))

            IconButton(modifier = Modifier
                .wrapContentSize()
                .background(color = colorResource(R.color.search_bag), shape = RoundedCornerShape(8.dp)),
                onClick = {}
            ) {
                Icon(modifier = Modifier
                    .size(width = 24.dp, height = 24.dp),
                    painter = painterResource(R.drawable.icon_filter), contentDescription = "",
                    tint = Color.White
                )
            }
        }//навигация поиск

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text("145 вакансий",
            fontSize = 14.sp,
            color = Color.White
             )
Spacer(modifier = Modifier
    .size(width = 100.dp, height = 14.dp))
        Row {
            //текст state
            Text(
            text =  "По соответствию",
            fontSize = 14.sp,
            color = Color.Blue)

            Icon(painter = painterResource(R.drawable.sorting), contentDescription = "",
                tint = Color.Blue,
                modifier = Modifier.size(width = 16.dp, height = 16.dp))
        }
    }

    }

}