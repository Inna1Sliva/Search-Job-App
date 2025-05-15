package com.it.shka.searchjobapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.it.shka.searchjobapp.model.NavSearchScreen
import com.it.shka.searchjobapp.screens.VacanciesScreen

@Composable
fun RootNavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController= navController,
        startDestination= NavSearchScreen.ScreenVacancies.route
    ){
        composable(NavSearchScreen.ScreenVacancies.route) {
            VacanciesScreen()

        }

    }
}