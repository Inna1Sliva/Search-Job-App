package com.it.shka.searchjobapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.it.shka.searchjobapp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.it.shka.searchjobapp.viewmodel.DataViewModel


@Composable
fun DetailsScreen(viewModel: DataViewModel, navController: NavController){
    val vacancy = viewModel.vacancyDetail.collectAsState()
    val statScroll = rememberScrollState()
    vacancy.value.forEach { vacancy->
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
    ) {
        // навигация
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .clickable{
                       navController.popBackStack()
                    }
                    .size(width = 24.dp, height = 24.dp),
                painter = painterResource(R.drawable.icon_back),
                tint = Color.White,
                contentDescription = ""
            )
            Spacer(
                modifier = Modifier
                    .width(200.dp)
            )
            Icon(
                modifier = Modifier
                    .size(width = 28.dp, height = 28.dp),
                painter = painterResource(R.drawable.icon_eye),
                contentDescription = null,
                tint = Color.White
            )
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(width = 28.dp, height = 28.dp),
                painter = painterResource(R.drawable.icon_share),
                contentDescription = null,
                tint = Color.White
            )
            //icon favorit
            if (vacancy.favorite?.isNotEmpty()==true){
                Icon(
                    modifier = Modifier
                        .clickable {
                            viewModel.deletIsFavorit(vacancy.id)
                        }
                        .size(width = 28.dp, height = 28.dp),
                    painter = painterResource(R.drawable.favorite),
                    tint = colorResource(R.color.Special_Blue),
                    contentDescription = "folover"
                )
            }else{
                Icon(
                    modifier = Modifier
                        .size(width = 28.dp, height = 28.dp),
                    painter = painterResource(R.drawable.icon_favorits),
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }// end navigation row

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(statScroll)
        ) {
            //text title
            Text(
                text = vacancy.title,
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.Bold))// w600
            )//
            //text full
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = vacancy.full,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
            )
            //Требуемый опыт: от 1 года до 3 лет
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = vacancy.previewText ,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
            )
            //Полная занятость, полный день
            Text(
                modifier = Modifier
                    .padding(top = 6.dp),
                text = vacancy.pull,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
            )

            // row looked peupel
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier

                        .wrapContentSize()
                        .background(
                            color = colorResource(R.color.Special_Dark_green),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                          .width(130.dp)
                            .wrapContentSize(),
                        text = "${vacancy.appliedNumber} человек уже откликнулись",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.sf_pro_display_regular,
                                FontWeight.W400
                            )
                        ),
                        overflow = TextOverflow.Clip
                    )
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 16.dp, height = 16.dp),
                        painter = painterResource(R.drawable.icon_look),
                        contentDescription = ""
                    )
                }

                //2 row click
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentSize()
                        .background(
                            color = colorResource(R.color.Special_Dark_green),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(130.dp)
                            .wrapContentSize(),
                        text = "${vacancy.lookingNumber} человека сейчас смотрят",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.sf_pro_display_regular,
                                FontWeight.W400
                            )
                        ),
                        overflow = TextOverflow.Clip
                    )
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 16.dp, height = 16.dp),
                        painter = painterResource(R.drawable.icon_look),
                        contentDescription = ""
                    )
                }
            }// end row looked peupel
            //box map
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(134.dp)
                    .background(
                        color = colorResource(R.color.color_bag_map),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                //row  adress
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 10.dp)
                ) {
                    Text(
                        text = vacancy.town,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.sf_pro_display_regular,
                                FontWeight.W400
                            )
                        ),

                        )
                    Icon(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(width = 16.dp, height = 16.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(R.drawable.icon_check),
                        contentDescription = null,
                        tint = colorResource(R.color.Basic_Grey_3)
                    )
                }// END row  adress

                //box mapa location work
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .background(
                            color = colorResource(R.color.Basic_Grey_3),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) { }

                // text adress
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 10.dp),
                    text = "${vacancy.town}, ${vacancy.street},${vacancy.house}",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)),
                )
            }
            // text
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(top = 16.dp),
                text = vacancy.description,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)),
                overflow = TextOverflow.Clip
            )
            // text description
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = "Ваши задачи",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600)),
            )
            // text responsibilities
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                text = vacancy.responsibilities,
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
            )
            //Задайте вопрос работодателю
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "Задайте вопрос работодателю",
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                text = "Он получит его с откликом на вакансию",
                color = colorResource(R.color.Basic_Grey_3),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
            )
            //ready-made questions
            Box(modifier = Modifier
                .padding(top = 5.dp)
                .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp))
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp)),
                    text = "Где распологается место работы?",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
                )
            }
            Box(modifier = Modifier
                .padding(top = 5.dp)
                .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp))
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp)),
                    text = "Какой график работы?",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
                )
            }
            Box(modifier = Modifier
                .padding(top = 5.dp)
                .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp))
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp)),
                    text = "Вакансия открыта?",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
                )
            }
            Box(modifier = Modifier
                .padding(top = 5.dp)
                .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp))
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = colorResource(R.color.Basic_Grey_2), shape = RoundedCornerShape(50.dp)),
                    text = "Какая оплата труда?",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .background(color = colorResource(R.color.button_color), shape = RoundedCornerShape(8.dp)),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.button_color),
                    contentColor = colorResource(R.color.button_color)
                )) {
                Text(
                    text = "Откликнуться",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600))
                )
            }




        }
    }

    }
}