package com.it.shka.searchjobapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.it.shka.searchjobapp.viewmodel.DataViewModel
import com.it.shka.searchjobapp.R
import com.it.shka.searchjobapp.rout.RouteMainContent

@Composable
fun BottomNavigation(navController: NavHostController, viewModel: DataViewModel){
    val fav by viewModel.vacancyLive.observeAsState()
    var bageText: String = remember { mutableStateOf("").toString() }

        bageText =fav.toString()

    NavigationBar(modifier = Modifier
        .background(color = colorResource(R.color.search_bag)),
        containerColor = Color.Black
    ) {
        val currentRoute =currentRoute(navController)
        NavigationBarItem(
                icon = {

                    Icon( modifier = Modifier
                            .size(width = 24.dp, height = 24.dp),
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = "Поиск",
                        )


                },
                label = {
                    Text("Поиск", fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Blue,
                    indicatorColor = Color.Transparent
                ),
                selected = currentRoute == RouteMainContent.MainSearch.route,
                onClick = {
                    navController.navigate(RouteMainContent.MainSearch.route){
                        launchSingleTop = true
                        restoreState= true
                }

                }
        )
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        if (bageText.toInt() > 0){
                        Badge(containerColor = Color.Red){

                                Text(text = "$bageText",
                                    color = Color.White)
                            }
                        }}) {

                    Icon( modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                        painter = painterResource(R.drawable.icon_favorits),
                        contentDescription = "Избранное",
                    )
                }

            },
            label = {
                Text("Избранное", fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)))
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            ),
            selected = currentRoute == RouteMainContent.FavoritesScreen.route,
            onClick = {
                navController.navigate(RouteMainContent.FavoritesScreen.route){
                    launchSingleTop = true
                    restoreState= true
                }

            }
        )
        NavigationBarItem(
            icon = {

                Icon( modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                        painter = painterResource(R.drawable.icon_response),
                        contentDescription = "Отклики",
                    )


            },
            label = {
                Text("Отклики", fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)))
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            ),
            selected = currentRoute == RouteMainContent.ResponseScreen.route,
            onClick = {
                navController.navigate(RouteMainContent.ResponseScreen.route){
                    launchSingleTop = true
                    restoreState= true
                }

            }
        )
        NavigationBarItem(
            icon = {

                Icon( modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                        painter = painterResource(R.drawable.icon_message),
                        contentDescription = "Сообщение",
                    )


            },
            label = {
                Text("Сообщение", fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)))
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            ),
            selected = currentRoute == RouteMainContent.MessageScreens.route,
            onClick = {
                navController.navigate(RouteMainContent.MessageScreens.route){
                    launchSingleTop = true
                    restoreState= true
                }

            }
        )
        NavigationBarItem(
            icon = {

                Icon( modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                        painter = painterResource(R.drawable.icon_profile),
                        contentDescription = "Профиль",
                    )


            },
            label = {
                Text("Профиль", fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)))
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            ),
            selected = currentRoute == RouteMainContent.ProfileScreen.route,
            onClick = {
                navController.navigate(RouteMainContent.ProfileScreen.route){
                    launchSingleTop = true
                    restoreState= true
                }

            }
        )



    }

}



//метод для получения текущего маршрута в BottomNavigation
@Composable
fun currentRoute(navController: NavHostController): String?{
    val backStackEntry by navController.currentBackStackEntryAsState()
    return backStackEntry?.destination?.route
}