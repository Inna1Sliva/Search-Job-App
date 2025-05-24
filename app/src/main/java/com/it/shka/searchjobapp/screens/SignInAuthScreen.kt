package com.it.shka.searchjobapp.screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.it.shka.searchjobapp.R
import com.it.shka.searchjobapp.viewmodel.UserAuthViewModel

@Composable
fun SignInAuthScreen(navController: NavHostController, authViewModel: UserAuthViewModel) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
    ){
        Text(modifier = Modifier
            .padding(start = 16.dp, top= 64.dp),
            text = "Вход в личный кабинет",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily =  FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600))
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(141.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = colorResource(R.color.color_bag_map), shape = RoundedCornerShape(8.dp))){
            Column (modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()){
                Text(
                    text = "Поиск работы",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily =  FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W500))
                )
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                        .height(40.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(color = colorResource(R.color.search_bag), shape = RoundedCornerShape(8.dp)),

                    value = email.value,
                    onValueChange = {it->
                        email.value = it
                                    },

                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left,
                        fontStyle = FontStyle.Normal
                    ),
                    cursorBrush = SolidColor(Color.White),

                    decorationBox = {
                        Log.d("Validate", "Error")
                        Text(modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .align(Alignment.CenterHorizontally),
                            text = "${email.value.toString()}",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }




                )
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                    ){
                    Button(modifier = Modifier
                        .padding(top = 16.dp)
                        .size(width = 167.dp, height = 40.dp)
                        .background(color = colorResource(R.color.Special_Blue), shape = RoundedCornerShape(8.dp)),
                        onClick = {
                            Log.d("Validate", email.value.toString())
                                authViewModel.isValidEmailUser(email.value.toString(), context)
                            navController.navigate("signUp")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.Special_Blue),
                            contentColor = colorResource(R.color.Special_Blue))
                    ) {
                        Text(
                            text = "Продолжить",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily =  FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
                        )
                    }
                    Text(
                        text = "Войти с паролем",
                        fontSize = 14.sp,
                        color = colorResource(R.color.Special_Blue),
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
                    )

                }

            }
        }//
        Box (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(color = colorResource(R.color.color_bag_map), shape = RoundedCornerShape(8.dp))
        ){
            Column(modifier = Modifier
                .padding(16.dp),) {
                Text(
                    text = "Поиск сотрудников",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
                )
                Text(modifier = Modifier
                    .padding(top = 6.dp, bottom = 16.dp),
                    text = "Размещение вакансий и доступ к базе резюме",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
                )
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(32.dp)
                    .background(color = colorResource(R.color.button_color), shape = RoundedCornerShape(50.dp)),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.button_color),
                        contentColor = colorResource(R.color.button_color))
                ) {
                    Text(
                        text = "Я ищу сотрудников",
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400))
                    )
                }
            }


        }
    }
}