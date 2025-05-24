package com.it.shka.searchjobapp.rout

sealed class RouteMainContent(val route: String) {
    object MainSearch: RouteMainContent("Поиск")
    object  FavoritesScreen: RouteMainContent("Избранное")
}