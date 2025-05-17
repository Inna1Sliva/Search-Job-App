package com.it.shka.data.model

data class Vacancy(
    val address: List<String> ,
    val appliedNumber: String = "",
    val company: String ="" ,
    val description: String="",
    val experience: Experience,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int,
    val publishedDate: String,
    val questions: List<String>,
    val responsibilities: String,
    val salary: Salary,
    val schedules: List<String>,
    val title: String
)