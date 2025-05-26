package com.it.shka.searchjobapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.it.shka.searchjobapp.dialog.DialogScreen
import com.it.shka.searchjobapp.dialog.DialogTwoScreen
import com.it.shka.searchjobapp.rout.RouteMainSearch
import com.it.shka.searchjobapp.screens.DetailsScreen
import com.it.shka.searchjobapp.screens.SearchScreen
import com.it.shka.searchjobapp.screens.VacanciesScreen
import com.it.shka.searchjobapp.viewmodel.DataViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainSearch(viewModel:DataViewModel){
    val navController  = rememberNavController()
    NavHost(
        navController= navController,
        startDestination= RouteMainSearch.SearchScreen.route
    ){
        composable(RouteMainSearch.SearchScreen.route){
            SearchScreen(viewModel, navController)
        }
        composable(RouteMainSearch.VacanciesScreen.route) {
            VacanciesScreen(viewModel, navController)
        }
        composable (RouteMainSearch.DetailsScreen.route){
            DetailsScreen(viewModel)
        }
        dialog (RouteMainSearch.DialogScreen.route) {
            DialogScreen(navController,viewModel)
        }

        dialog (RouteMainSearch.DialogTwoScreen.route){
            DialogTwoScreen(navController, viewModel)
        }
    }
}