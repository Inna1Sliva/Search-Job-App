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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.it.shka.searchjobapp.DataViewModel
import com.it.shka.searchjobapp.IconId
import com.it.shka.searchjobapp.R
//

@Composable
fun SearchScreen(viewModel:DataViewModel){
   // var offer = remember { mutableStateListOf<Offer>() }
    val offer = viewModel.offerState.collectAsState()
 //  val offer = viewModel.offerState.collectAsState(emptyList())


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
                        .width(280.dp) ,
                        value = "textWhere.value",
                        onValueChange = {//textWhere.value = it
                             },
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
        }//поиск
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            ){
            items(listOf(offer)){ data->
                data.value.forEach {
                    Box(modifier = Modifier
                        .size(width = 150.dp, height = 132.dp)
                        .padding(end = 10.dp)
                        .background(color = colorResource(R.color.vacancy_bag),
                            shape = RoundedCornerShape(8.dp))){
                        Column (modifier = Modifier
                            .padding(8.dp)
                        ){
                            // Иконка
                            val iconId = IconId.fromIconIdStrinf(it.id)
                            val iconRes = iconId?.let { getIconResource(it) }
                            if (iconRes != null){
                                Icon(modifier = Modifier
                                    .size(width = 32.dp, height = 32.dp),
                                    painter = painterResource(id = iconRes),
                                    contentDescription = it.id)
                            }else{
                                Spacer(modifier = Modifier
                                    .size(width = 32.dp, height = 32.dp))
                            }
                            //Teкст
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top=10.dp),
                                text =it.title ,
                                color = Color.White,
                                fontSize = 14.sp,
                                overflow = TextOverflow.Clip
                            )
                            //кнопка boolean
                            if (it.button.isNotEmpty()){
                                Text(text = it.button, color = Color.Green, fontSize = 14.sp)
                            }else{
                                Text(text = "", color = Color.Green, fontSize = 14.sp)

                            }
                        }
                    }
                }

            }
        }
        // текст вакансии
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top= 20.dp),
            text = "Вакансии для вас",
            color = Color.White,
            fontSize = 20.sp
        )

    }
}
@Composable
fun getIconResource(iconId: IconId): Int{
return when(iconId){
    IconId.NEAR_VACANCIES -> R.drawable.icon_location
    IconId.LEVEL_UP_RESURS -> R.drawable.icon_level_up_resume
    IconId.TEMPORARY_JOB -> R.drawable.icon_temporary_job
}
}
