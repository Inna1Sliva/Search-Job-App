package com.it.shka.searchjobapp.dialog

import com.it.shka.searchjobapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.it.shka.searchjobapp.viewmodel.DataViewModel

@Composable
fun DialogScreen (navController: NavHostController, viewModel: DataViewModel) {
    val text = remember { mutableStateOf("Добавить сопроводительное") }
    var showDialog  =viewModel.stateDialog.collectAsState()

    if (showDialog.value) {
        val density = LocalDensity.current
        Dialog(onDismissRequest = {
            viewModel.closeDialog()
            navController.popBackStack()
        }) {
            val offsetPx = with(density) { 29.dp.toPx() }
            Box(
                modifier = Modifier
                    .clickable {
                       // viewModel.closeDialog()
                        navController.popBackStack()
                    }
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        // .padding(bottom = 56.dp)
                        .fillMaxWidth()
                        .offset(y = (-offsetPx).dp)
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )

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

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp)
                            .clickable {
                                viewModel.openDialogTwo()
                                viewModel.closeDialog()
                                navController.popBackStack()
                                navController.navigate("dialog_two")
                            },
                        text = text.value,
                        color = colorResource(R.color.button_color),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.sf_pro_display_regular,
                                FontWeight.W600
                            )
                        )
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
                            viewModel.closeDialog()
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
}