package com.it.shka.searchjobapp.rout


sealed class RouteMainScreen(val route: String){
    object MainScreen: RouteMainScreen("main")
    object SignInAuthScreen: RouteMainScreen("signIn")
    object  SignUpAuthScreen: RouteMainScreen("signUp")
}

