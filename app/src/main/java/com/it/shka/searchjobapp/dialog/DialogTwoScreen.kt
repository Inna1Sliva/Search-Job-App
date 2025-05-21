package com.it.shka.searchjobapp.dialog

import com.it.shka.searchjobapp.R
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.it.shka.searchjobapp.DataViewModel

@Composable
fun DialogTwoScreen (navController: NavController, viewModel: DataViewModel) {
    val showDialogTwo = viewModel.stateDialogTwo.collectAsState()
    val focusRequester = remember { FocusRequester() }
    var editText = remember { mutableStateOf("") }
    if (showDialogTwo.value) {
        Dialog(
            onDismissRequest = { viewModel.closeDialogTwo()
                navController.popBackStack() }
        ) {
            Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        ),
                  horizontalAlignment = Alignment.CenterHorizontally

                ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .size(width = 38.dp, height = 5.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(
                            color = colorResource(R.color.Basic_Grey_2),
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(width = 48.dp, height = 48.dp),
                        painter = painterResource(R.drawable.icon_user),
                        contentDescription = "avatar user"
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Резюме для отклика",
                            color = colorResource(R.color.Basic_Grey_3),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.sf_pro_display_regular,
                                    FontWeight.W400
                                )
                            )
                        )

                        Text(
                            modifier = Modifier
                                .padding(top = 6.dp),
                            text = "UI/UX дизайнер",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.sf_pro_display_regular,
                                    FontWeight.W500
                                )
                            )
                        )
                    }

                }//end row
                Spacer(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(R.color.Basic_Grey_2),)
                )
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .padding(start = 16.dp),
                    value = editText.value,
                    onValueChange = {
                        editText.value = it
                    },
                    decorationBox = {
                        Text(
                            text = "Ваше сопроводительное письмо",
                            color = colorResource(R.color.Basic_Grey_3),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.sf_pro_display_regular,
                                    FontWeight.W600
                                )
                            )
                        )
                    }
                )



                    Button(
                        modifier = Modifier
                            .padding(19.dp)
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.button_color),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        onClick = {
                            viewModel.closeDialogTwo()
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.button_color),
                            contentColor = colorResource(R.color.button_color)
                        )
                    ) {
                        Text(
                            text = "Откликнуться",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.sf_pro_display_regular,
                                    FontWeight.W600
                                )
                            )
                        )
                    }
                }
            }
        }

}