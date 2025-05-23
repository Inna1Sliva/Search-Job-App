package com.it.shka.searchjobapp.screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.it.shka.searchjobapp.R
import kotlin.text.isEmpty

@Composable
fun SignUpAuthScreen(){
    val otpVal = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 162.dp, start = 16.dp, end = 16.dp)) {
            Text(

                text = "Отправили код на example@mail.ru",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600))
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp),
                text = "Напишите его, чтобы подтвердить, что это вы, а не кто-то другой входит в личный кабинет",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600))
            )

            OTPTextFields(onFilled ={
                otpVal.value = it.toString()
            } )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(color = colorResource(R.color.Special_Dark_blue), shape = RoundedCornerShape(8.dp)),
                onClick = {


                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.Special_Dark_blue),
                    contentColor = colorResource(R.color.Special_Dark_blue))
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp),
                    text = "Подтвердить",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W600))
                )
            }
        }

        // здесь кнопки или текст
    }

}

@Composable
fun OTPTextFields(


    onFilled:(code:String)-> Unit
){
    val otpLength:Int = 4
    var otpValues =remember { mutableStateListOf(*Array(otpLength){""}) }
    val focusRequester= List(otpLength){ FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row (
        modifier = Modifier.height(50.dp)
    ) {
        otpValues.forEachIndexed { index, value ->
            OutlinedTextField(
                modifier = Modifier
                    .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                    .size(width = 50.dp, height = 50.dp)
                    .focusRequester(focusRequester[index])
                    .align(Alignment.CenterVertically)

                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace) {
                            if (otpValues[index].isEmpty() && index > 0) {
                                otpValues[index] = ""
                                focusRequester[index - 1].requestFocus()
                            } else {
                                otpValues[index] = ""
                            }
                            true
                        } else {
                            false
                        }
                    },
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otpValues[index] = newValue
                        if (newValue.isNotEmpty()) {
                            if (index < otpLength - 1) {
                                focusRequester[index + 1].requestFocus()
                            } else {
                                keyboardController?.hide()
                                otpValues.joinToString(separator = ""){
                                    onFilled(it.toString()).toString()
                                }

                            }
                        }
                    } else {
                        if (index < otpLength - 1) focusRequester[index + 1].requestFocus()


                    }
                },
                //123 visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.Basic_Grey_2),
                    cursorColor = Color.White,
                    unfocusedContainerColor = colorResource(R.color.Basic_Grey_2),
                    focusedIndicatorColor = colorResource(R.color.Basic_Grey_2),
                    unfocusedIndicatorColor = colorResource(R.color.Basic_Grey_2),
                    focusedTextColor = colorResource(R.color.Basic_Grey_3),
                    unfocusedTextColor = colorResource(R.color.Basic_Grey_3)
                )
            )
            Spacer(
                modifier = Modifier
                    .size(width = 10.dp, height = 50.dp)
            )
        }
        LaunchedEffect(Unit) {
            focusRequester.first().requestFocus()
        }


    }
}