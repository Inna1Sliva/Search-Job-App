package com.it.shka.data

import android.util.Log
//mport androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.it.shka.data.model.Offer
import com.it.shka.data.model.Vacancy
import com.it.shka.domain.DataRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class ImplDataRepository(private val dataReference: FirebaseDatabase ): DataRepository {
    private val _offerState: MutableStateFlow<List<Offer>> = MutableStateFlow(emptyList())
    val offerState: StateFlow<List<Offer>> get() = _offerState
   // var vacancy = MutableLiveData<List<Vacancy>>()
    private val _vacancyState: MutableStateFlow<List<Vacancy>> = MutableStateFlow(emptyList())
    val vacancyState: StateFlow<List<Vacancy>> get() = _vacancyState

    val _favoritState = MutableStateFlow<List<Vacancy>>(emptyList())
    val favoritsState: StateFlow<List<Vacancy>> = _favoritState
    val favoritCount: StateFlow<Int> = MutableStateFlow(0)

    init {
   //     vacancy = MutableLiveData()
    }
    fun getVacancyF(): Flow<List<Vacancy>> = callbackFlow {
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var addList = mutableListOf<Vacancy>()
                for (dataSnaphot in snapshot.children){
                    val data = dataSnaphot.getValue(Vacancy::class.java)
                    addList.add(data!!)
                }
                trySend(addList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

        }
        dataReference.getReference("vacancies").addValueEventListener(listener)
        awaitClose{dataReference.getReference("vacancies").removeEventListener(listener)}
    }
   override suspend fun getOffer() {
        try {
            val snapshot = dataReference.getReference("offers").get().await()
            if (snapshot.exists()) {
                var offerList = mutableListOf<Offer>()
                for (dataSnaphot in snapshot.children) {
                    val data = dataSnaphot.getValue(Offer::class.java)
                    offerList.add(data!!)
                    Log.d("OFFER", "${data}")

                }
                _offerState.value = offerList

            }
        } catch (e: Exception) {
            _offerState.value = emptyList()
        }

    }

    override suspend fun getVacancy(){
        try {
            val snapshot = dataReference.getReference("vacancies").get().await()
            if (snapshot.exists()) {
                var vacancyList = mutableListOf<Vacancy>()
                for (dataSnaphot in snapshot.children) {
                    val data = dataSnaphot.getValue(Vacancy::class.java)
                    vacancyList.add(data!!)
                    Log.d("OFFER", "${data}")

                }
                _vacancyState.value = vacancyList
              //  vacancy.postValue(vacancyList)

            }
        } catch (e: Exception) {
            _vacancyState.value = emptyList()
           // vacancy.postValue(emptyList())

        }
    }

    override suspend fun deletIsFavorit(vacancyId: String) {
        val vacancyData = mapOf(
            "favorite" to ""
        )
        dataReference.getReference("vacancies").child(vacancyId).updateChildren(vacancyData)
            .addOnSuccessListener {
                updateFavoritCount()
                updateNVacancy()
                Log.d("Vacancy", "Успех")
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    override suspend fun setIsFavorit(vacancyId: String) {
        val vacancyData = mapOf(
            "favorite" to "1"
        )
        dataReference.getReference("vacancies").child(vacancyId).updateChildren(vacancyData)
            .addOnSuccessListener {
                updateFavoritCount()
                updateNVacancy()
                Log.d("Vacancy", "Успех")
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }

    }



    fun updateFavoritCount():Flow<List<Vacancy>>{
        return getVacancyF()
    }
    fun updateNVacancy():Flow<List<Vacancy>>{
        return getVacancyF()
    }
}