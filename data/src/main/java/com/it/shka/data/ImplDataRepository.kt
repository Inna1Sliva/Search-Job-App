package com.it.shka.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.it.shka.data.model.Offer
import com.it.shka.domain.DataRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class ImplDataRepository(private val dataReference: FirebaseDatabase ): DataRepository {
   private val _offerState:MutableStateFlow<List<Offer>> = MutableStateFlow(emptyList())
   val offerState:StateFlow<List<Offer>> get() = _offerState

    fun getOfferData(): Flow<List<Offer>> = callbackFlow {
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val list = mutableListOf<Offer>()
                for (dataSnaphot in snapshot.children){
                    val data = dataSnaphot.getValue(Offer::class.java)
                    data?.let { list.add(it.copy(id=dataSnaphot.key ?:"")) }
                }
                trySend(list).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
              close(error.toException())
            }

        }
        dataReference.getReference("offers").addValueEventListener(listener)
        awaitClose{ dataReference.getReference("offers").removeEventListener(listener)}
    }
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
        TODO("Not yet implemented")
    }
}