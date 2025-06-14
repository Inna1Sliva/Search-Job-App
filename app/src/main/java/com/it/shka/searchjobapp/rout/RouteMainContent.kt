package com.it.shka.searchjobapp.rout

sealed class RouteMainContent(val route: String) {
    object DetailsScreen: RouteMainContent("detaile")
    object MainSearch: RouteMainContent("Поиск")
    object  FavoritesScreen: RouteMainContent("Избранное")
    object ResponseScreen:RouteMainContent("Отклики")
    object MessageScreens: RouteMainContent("Сообщение")
    object ProfileScreen:RouteMainContent("Профиль")
}