package com.it.shka.searchjobapp.rout

sealed class RouteMainSearch(val route: String) {
    object SearchScreen:RouteMainSearch("start_search")
    object VacanciesScreen:RouteMainSearch("vacancies")
    object DetailsScreen:RouteMainSearch("detaile")
    object DialogScreen:RouteMainSearch("dialog")
    object DialogTwoScreen:RouteMainSearch("dialog_two")
}