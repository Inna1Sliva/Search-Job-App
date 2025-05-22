package com.it.shka.searchjobapp.screens

import android.health.connect.datatypes.units.Length
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.sin

@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    val otpVal: String? = null
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        OTPTextFields(length = 4) {getOpt-> otpVal
        }
        // здесь кнопки или текст
    }

}

@Composable
fun OTPTextFields(
    modifier: Modifier = Modifier,
    length: Int,
    onFilled:(code:String)-> Unit
){
    var code :List<Char> by remember { mutableStateOf(listOf()) }
    val focusRequester: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
            repeat(length){
                temp.add(FocusRequester())
            }
        temp
    }
    Row (
        modifier = Modifier.height(50.dp)
    ){
        (0 until length).forEach { index->
            OutlinedTextField(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .focusOrder(focusRequester = focusRequester[index]){
                        focusRequester[index +1].requestFocus()
                    },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center, color = Color.Black
                ),
                singleLine = true,
                value = code.getOrNull(index = index)?.takeIf {
                    it.isDigit()
                }?.toString() ?: "",
                onValueChange = { value: String->
                    if (focusRequester[index].freeFocus()){
                        val temp = code.toMutableList()
                        if (value == ""){
                            if (temp.size > index){
                                temp.removeAt(index = index)
                                code = temp
                                focusRequester.getOrNull(index - 1)?.requestFocus()
                            }
                        }else{
                            if (code.size> index){
                                temp[index] = value.getOrNull(0)?: ' '
                            }else{
                                temp.add(value.getOrNull(0)?: ' ')
                                code = temp
                                focusRequester.getOrNull(index + 1)?.requestFocus()?: onFilled
                                code.joinToString(separator = "")
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.width(15.dp))
        }

    }
}