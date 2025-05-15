package com.it.shka.searchjobapp.model


sealed class NavSearchScreen(val route: String){
    object ScreenVacancies: NavSearchScreen("vacancies")
    object MainContent: NavSearchScreen("mainContent")
}
