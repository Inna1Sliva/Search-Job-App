package com.it.shka.searchjobapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.shka.data.ImplDataRepository
import com.it.shka.data.model.Offer
import com.it.shka.domain.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DataViewModel(private val dataRepository: ImplDataRepository): ViewModel() {
   private val _offerState= MutableStateFlow<List<Offer>>(emptyList())
    val offerState:StateFlow<List<Offer>> = dataRepository.offerState

    init {
        viewModelScope.launch {
            dataRepository.getOffer()
        }
      //  viewModelScope.launch {
         //   dataRepository.getOfferData().collect { list->
           //     _offerState.value = list

          //  }
       // }
    }

}