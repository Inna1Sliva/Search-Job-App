package com.it.shka.data

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.it.shka.data.model.Offer
import com.it.shka.data.model.Vacancy
import com.it.shka.domain.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await


class ImplDataRepository(private val dataReference: FirebaseDatabase ): DataRepository {
   private val _offerState:MutableStateFlow<List<Offer>> = MutableStateFlow(emptyList())
   val offerState:StateFlow<List<Offer>> get() = _offerState

    private val _vacancyState:MutableStateFlow<List<Vacancy>> = MutableStateFlow(emptyList())
    val vacancyState:StateFlow<List<Vacancy>> get() = _vacancyState

    override suspend fun getOffer() {
        try {
            val snapshot = dataReference.getReference("offers").get().await()
            if (snapshot.exists()){
                var offerList = mutableListOf<Offer>()
                for (dataSnaphot in snapshot.children){
                    val data = dataSnaphot.getValue(Offer::class.java)
                    offerList.add(data!!)
                    Log.d("OFFER", "${data}")

                }
                _offerState.value= offerList

            }
        }catch (e:Exception){
            _offerState.value=emptyList()
        }

    }

    override suspend fun getVacancy() {
        try {
            val snapshot = dataReference.getReference("vacancies").get().await()
            if (snapshot.exists()){
                var vacancyList = mutableListOf<Vacancy>()
                for (dataSnaphot in snapshot.children){
                    val data = dataSnaphot.getValue(Vacancy::class.java)
                    vacancyList.add(data!!)
                    Log.d("OFFER", "${data}")

                }
                _vacancyState.value = vacancyList

            }
        }catch (e:Exception){
            _vacancyState.value = emptyList()
        }
    }
}