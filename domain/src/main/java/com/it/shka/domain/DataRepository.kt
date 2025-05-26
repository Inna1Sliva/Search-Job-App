package com.it.shka.domain

import java.util.concurrent.Flow

interface DataRepository {
    suspend fun getOffer()
    suspend fun getVacancy()
    suspend fun deletIsFavorit(vacancyId: String)
    suspend fun setIsFavorit(vacancyId: String)
}