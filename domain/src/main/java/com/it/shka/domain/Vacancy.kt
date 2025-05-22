package com.it.shka.domain

data class Vacancy(
    val appliedNumber: String="",
    val company: String="",
    val description: String="",
    val full: String="",
    val fullP: String="",
    val house: String="",
    val id: String="",
    val favorite: String?= "",
    val short:String="",
    val lookingNumber: String?="",
    val previewText: String="",
    val publishedDate: String="",
    val pull: String="",
    val questions: List<String>? =null,
    val responsibilities: String="",
    val street: String="",
    val title: String="",
    val town: String=""
)