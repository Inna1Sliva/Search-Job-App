package com.it.shka.domain

interface DataRepository {
    suspend fun getOffer()
    suspend fun getVacancy()
    suspend fun setIsFavorit(vacancyId: String)
}