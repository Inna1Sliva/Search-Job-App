package com.it.shka.searchjobapp


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.shka.data.ImplDataRepository
import com.it.shka.data.model.Offer
import com.it.shka.data.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch


class DataViewModel(private val dataRepository: ImplDataRepository): ViewModel() {
    private val _vacancyDetail = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancyDetail: StateFlow<List<Vacancy>> get() = _vacancyDetail

    val _favoritState = MutableStateFlow<List<Vacancy>>(emptyList())

    val favoritsState: StateFlow<List<Vacancy>>  = _favoritState

    val offerState:StateFlow<List<Offer>> = dataRepository.offerState
    val vacancyState:StateFlow<List<Vacancy>> = dataRepository.vacancyState

    init {
        viewModelScope.launch {
            dataRepository.getOffer()
        }
       viewModelScope.launch {
            dataRepository.getVacancy()
       }
        getFavoritVacancy()
    }
    fun getFirstNItems(): StateFlow<List<Vacancy>>{
         val _nState = MutableStateFlow<List<Vacancy>>(emptyList())
        viewModelScope.launch {
            val firstThree = mutableListOf<Vacancy>()
            vacancyState.collect { list->
                for (i in list.indices){
                    if (i >= 3 ) break
                    firstThree.add(list[i])
                }
                _nState.value = firstThree
            }

        }
        return _nState
    }
    fun setDetailVacancy(vacancy: Vacancy){
     //   val _vacancyState = MutableStateFlow<List<Vacancy>>(emptyList())
        viewModelScope.launch {
            val firstThree = mutableListOf<Vacancy>()
            firstThree.add(vacancy)
            _vacancyDetail.value = firstThree
        }
    }
    fun getFavoritVacancy(){
        viewModelScope.launch {
            vacancyState.collect { vacancy->
                val addFavorit = mutableListOf<Vacancy>()
                for (favorit in  vacancy){
                    if (favorit.lookingNumber?.isEmpty()==true){
                        addFavorit.add(favorit)
                    }
                    _favoritState.value = addFavorit
                }
            }

        }

    }


}