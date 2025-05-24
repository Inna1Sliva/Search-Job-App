package com.it.shka.searchjobapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.it.shka.data.model.Vacancy
import com.it.shka.searchjobapp.viewmodel.DataViewModel
import com.it.shka.searchjobapp.R

@Composable
fun FavoritesScreen(viewModel: DataViewModel){
    val favorits = viewModel.favoritsState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
    ) {
        Text(
            modifier = Modifier
                .padding( start = 16.dp, top = 64.dp),
            text = "Избранное",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600))
        )
        //text count
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "1 вакансия",
            fontSize = 14.sp,
            color = colorResource(R.color.Basic_Grey_3),
            fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
        )
        LazyColumn(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ) {
           items (listOf(favorits)){ list->
               list.value.forEach {vacancy->
                   ItemFavorit(vacancy)

               }


           }


        }
    }
}
@Composable
fun ItemFavorit(vacancy: Vacancy){
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp)
            .clickable{}//onItemClick(vacancy)
            .background(
                color = colorResource(R.color.vacancy_bag),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .width(250.dp)
            ) {
                //текст просмотр
                if (vacancy.lookingNumber?.isNotEmpty() == true) {
                    Text(
                        text = "Сейчас просматривает ${vacancy.lookingNumber} человек",
                        color = colorResource(R.color.button_color),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left
                    )
                } else {
                    Text(
                        text = "",
                        color = colorResource(R.color.button_color),
                        fontSize = 14.sp
                    )
                }

                //наименование вакансии
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = vacancy.title,
                    color = Color.White,
                    fontSize = 16.sp
                ) //1500-2900 Br
                //зп
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = vacancy.full,
                    color = Color.White,
                    fontSize = 20.sp
                )


                //город
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = vacancy.town,
                    color = Color.White,
                    fontSize = 14.sp
                )
                //компани
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = vacancy.company,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                            .size(width = 16.dp, height = 16.dp),
                        painter = painterResource(R.drawable.icon_check),
                        contentDescription = "icon_check",
                        tint = colorResource(R.color.Basic_Grey_3)
                    )
                }
                //опыт работы //
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(width = 16.dp, height = 16.dp),
                        painter = painterResource(R.drawable.icon_skill),
                        contentDescription = "icon_check",
                        tint = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        text = vacancy.previewText,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                }
                // дата публикации
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = "${getMonthString(vacancy.publishedDate)}",
                    color = colorResource(R.color.Basic_Grey_3),
                    fontSize = 14.sp
                )

            }
            Spacer(
                modifier = Modifier
                    .width(50.dp)
            )
            //Добавить метод определения
            if (vacancy.favorite?.isNotEmpty() == true) {

                Icon(
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                    painter = painterResource(R.drawable.favorite),
                    tint = colorResource(R.color.Special_Blue),
                    contentDescription = "folover"
                )


            } else  {

                Icon(
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                    painter = painterResource(R.drawable.icon_favorits),
                    tint = colorResource(R.color.Basic_Grey_4),
                    contentDescription = "folover"
                )
            }


        }
        //button
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = colorResource(R.color.button_color),
                    shape = RoundedCornerShape(50.dp)
                ),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_color),
                contentColor = colorResource(R.color.button_color)
            )
        ) {
            Text(
                text = "Откликнуться",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}