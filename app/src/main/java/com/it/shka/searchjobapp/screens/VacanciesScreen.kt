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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.it.shka.data.model.Vacancy
import com.it.shka.searchjobapp.R
import java.nio.file.WatchEvent

//@Preview(showBackground = true)
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
        LazyColumn(modifier = Modifier
            .fillMaxWidth()){
            items(listOf<Vacancy>(

            )) { data->
                ItemListVacancy()
            }

        }

    }

}
@Preview(showBackground = true)
@Composable
fun ItemListVacancy(){//item: Vacancy
    Column (
        modifier = Modifier
            .wrapContentSize()
            .background(color = colorResource(R.color.vacancy_bag), shape = RoundedCornerShape(8.dp))
    ){
Row (modifier = Modifier
    .padding(16.dp)
    .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween

){
    Column(modifier = Modifier
        .width(219.dp)
    ) {
        //текст просмотр
       Text(text = "Сейчас просматривает 1 человек",
           color = colorResource(R.color.button_color),
           fontSize = 14.sp)
        //наименование вакансии
        Text(modifier = Modifier
            .padding(top = 5.dp),
            text = "Дизайнер для маркетплейсов Wildberries, Ozon",
            color = Color.White,
            fontSize = 16.sp) //1500-2900 Br
        //зп
        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = "1500-2900 Br",
            color = Color.White,
            fontSize = 20.sp)
        //город
        Text(modifier = Modifier
            .padding(top = 5.dp),
            text = "Минск",
            color = Color.White,
            fontSize = 14.sp)
            //адресс
        Row(modifier = Modifier
            .padding(top = 5.dp)) {
            Text(text = "Еком дизайн",
                color = Color.White,
                fontSize = 14.sp)
            Icon(modifier = Modifier
                .padding(start = 2.dp)
                .size(width = 16.dp, height = 16.dp),
                painter = painterResource(R.drawable.icon_check),
                contentDescription = "icon_check",
                tint = colorResource(R.color.Basic_Grey_3))
        }
            //опыт работы //
        Row(modifier = Modifier
            .padding(top = 5.dp)) {
            Icon(modifier = Modifier

                .size(width = 16.dp, height = 16.dp),
                painter = painterResource(R.drawable.icon_skill),
                contentDescription = "icon_check",
                tint = Color.White)

            Text(modifier = Modifier
                .padding(start = 5.dp),
                text = "Опыт от 1 года до 3 лет",
                color = Color.White,
                fontSize = 14.sp)

        }
        // дата публикации
        Text(modifier = Modifier
            .padding(top = 5.dp),
            text = "Опубликовано 16 февраля",
            color = colorResource(R.color.Basic_Grey_3),
            fontSize = 14.sp)

    }
    Spacer(modifier = Modifier
        .width(50.dp))
    //Добавить метод определения
    Icon(modifier = Modifier
        .size(width =24.dp, height = 24.dp ),
        painter = painterResource(R.drawable.icon_favorits),
        tint = colorResource(R.color.Basic_Grey_4),
        contentDescription = "folover")

}
        //button
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color =colorResource(R.color.button_color), shape = RoundedCornerShape(50.dp)),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_color),
                contentColor = colorResource(R.color.button_color)
            )) {
            Text(text = "Откликнуться",
                color = Color.White,
                fontSize = 14.sp)
        }
}

}