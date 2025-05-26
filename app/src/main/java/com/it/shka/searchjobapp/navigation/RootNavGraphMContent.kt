package com.it.shka.searchjobapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.it.shka.searchjobapp.viewmodel.DataViewModel
import com.it.shka.searchjobapp.rout.RouteMainContent
import com.it.shka.searchjobapp.screens.DetailsScreen
import com.it.shka.searchjobapp.screens.FavoritesScreen
import com.it.shka.searchjobapp.screens.MessageScreen
import com.it.shka.searchjobapp.screens.ProfileScreen
import com.it.shka.searchjobapp.screens.ResponseScreen


@Composable
fun MainContent(dataViewModel: DataViewModel){
    val navBottomNavigation  = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomNavigation(navBottomNavigation, dataViewModel)

        }
    ){innerPadding->
        NavHost (
            navController= navBottomNavigation,
            startDestination = RouteMainContent.MainSearch.route,
            Modifier.padding(innerPadding)
        ){

            composable(RouteMainContent.MainSearch.route) {
                MainSearch(dataViewModel)
            }
            composable (RouteMainContent.FavoritesScreen.route){
                FavoritesScreen(dataViewModel, navBottomNavigation)
            }
            composable (RouteMainContent.DetailsScreen.route){
                DetailsScreen(dataViewModel,navBottomNavigation)
            }
            composable(RouteMainContent.ResponseScreen.route) {
                ResponseScreen()
            }
            composable(RouteMainContent.MessageScreens.route) {
                MessageScreen()
            }
            composable(RouteMainContent.ProfileScreen.route) {
                ProfileScreen()
            }

        }

    }

}