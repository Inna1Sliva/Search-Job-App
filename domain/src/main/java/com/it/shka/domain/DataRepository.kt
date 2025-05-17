package com.it.shka.domain

interface DataRepository {
    suspend fun getOffer()
    suspend fun getVacancy()
}