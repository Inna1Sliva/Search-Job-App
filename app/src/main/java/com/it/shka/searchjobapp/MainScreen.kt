package com.it.shka.searchjobapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.it.shka.searchjobapp.model.BottomNavItem
import com.it.shka.searchjobapp.screens.MainSearch
import com.it.shka.searchjobapp.screens.VacanciesScreen



@Composable
fun MainScreen(dataViewModel: DataViewModel){
    val NavHostController  = rememberNavController()
    NavHost(
        navController= NavHostController,
        startDestination= "main"
    ){
        composable("main"){
            MainContent(NavHostController,dataViewModel)

        }
        composable("vacancies") {
            //VacanciesScreen(Da)

        }

    }


}
@Composable
fun MainContent(navController: NavHostController,
                dataViewModel: DataViewModel){
    val navBottomNavigation  = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomNavigation(navBottomNavigation)
          //  BadgedBox() { }
        }
    ){innerPadding->
        NavHost (
            navController= navBottomNavigation,
            startDestination = "Поиск",
            Modifier.padding(innerPadding)
        ){

            composable("Поиск") {
                MainSearch(dataViewModel)
                }


            }

        }

    }



@Composable
fun BottomNavigation(navController: NavHostController ){
    var bottomNavItems = listOf(
        BottomNavItem(R.drawable.icon_search, "Поиск"),
        BottomNavItem(R.drawable.icon_favorits, "Избранное"),
        BottomNavItem(R.drawable.icon_response, "Отклики"),
        BottomNavItem(R.drawable.icon_message, "Сообщение"),
        BottomNavItem(R.drawable.icon_profile, "Профиль"),
    )
    NavigationBar(modifier = Modifier
        .padding(top = 0.2.dp)
        .background(color = colorResource(R.color.search_bag)),
        containerColor = Color.Black
    ) {
        val currentRoute =currentRoute(navController)
        bottomNavItems.forEach { item->
            NavigationBarItem(
                icon = {
                    Icon( modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        )
                },
                label = {
                    Text(item.title, fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular, FontWeight.W400)))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Blue,
                    indicatorColor = Color.Transparent
                ),
                selected = currentRoute == item.title,
                onClick = {
                    navController.navigate(item.title){
                        launchSingleTop = true
                        restoreState= true
                }

                }
                )
        }

    }

}



//метод для получения текущего маршрута в BottomNavigation
@Composable
fun currentRoute(navController: NavHostController): String?{
    val backStackEntry by navController.currentBackStackEntryAsState()
    return backStackEntry?.destination?.route
}