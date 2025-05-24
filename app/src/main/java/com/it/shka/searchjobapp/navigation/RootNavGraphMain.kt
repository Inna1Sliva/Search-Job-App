package com.it.shka.searchjobapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.it.shka.searchjobapp.viewmodel.DataViewModel
import com.it.shka.searchjobapp.viewmodel.UserAuthViewModel
import com.it.shka.searchjobapp.rout.RouteMainScreen
import com.it.shka.searchjobapp.screens.SignInAuthScreen
import com.it.shka.searchjobapp.screens.SignUpAuthScreen

@Composable
fun MainScreen(dataViewModel: DataViewModel, authViewModel: UserAuthViewModel, route: String){
    val NavHostController  = rememberNavController()
    NavHost(
        navController= NavHostController,
        startDestination= route
    ){
        composable(RouteMainScreen.MainScreen.route){
            MainContent(dataViewModel)

        }
        composable(RouteMainScreen.SignInAuthScreen.route) {
            SignInAuthScreen(NavHostController, authViewModel)

        }
        composable(RouteMainScreen.SignUpAuthScreen.route) {
            SignUpAuthScreen( authViewModel,NavHostController)

        }

    }


}