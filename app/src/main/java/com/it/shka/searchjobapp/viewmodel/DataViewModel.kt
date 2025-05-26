package com.it.shka.searchjobapp.viewmodel


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.shka.data.ImplDataRepository
import com.it.shka.data.model.Offer
import com.it.shka.data.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DataViewModel(private val dataRepository: ImplDataRepository): ViewModel() {
    val stateDialog = MutableStateFlow(false)
    val stateDialogTwo = MutableStateFlow(false)

    private   val _vacancyLive = MutableLiveData<Int>(0)
  val vacancyLive : MutableLiveData<Int> = _vacancyLive

    private val _vacancyDetail = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancyDetail: StateFlow<List<Vacancy>> get() = _vacancyDetail
    //dataRepository.favoritsState
    val _favoritState = MutableStateFlow<List<Vacancy>>(emptyList())

    val favoritsState: StateFlow<List<Vacancy>>  = _favoritState
    val favoritCount : StateFlow<Int> = dataRepository.favoritCount

    val offerState:StateFlow<List<Offer>> = dataRepository.offerState
    //dataRepository.vacancyState
    val _vacancyState = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancyState:StateFlow<List<Vacancy>> = _vacancyState

    private val _nVacancyState = MutableStateFlow<List<Vacancy>>(emptyList())
    val nVacancyState: StateFlow<List<Vacancy>> = _nVacancyState

    init {
        viewModelScope.launch {
            dataRepository.getOffer()
        }
       viewModelScope.launch {
            dataRepository.getVacancy()
       }
        viewModelScope.launch {
            dataRepository.getVacancyF().collect {vacancy->
                _vacancyState.value = vacancy
                val addFavorit = mutableListOf<Vacancy>()
                for (f in vacancy ) {
                    if (f.favorite?.isNotEmpty() == true) {
                        addFavorit.add(f)
                    }
                    _vacancyLive.postValue(addFavorit.size)
                    _favoritState.value = addFavorit
                }
            }

        }
        viewModelScope.launch {
            dataRepository.updateNVacancy().collect {vacancy->
                val firstThree = mutableListOf<Vacancy>()
                for (i in vacancy.indices){
                    if (i >= 3 ) break
                    firstThree.add(vacancy[i])
                }
                _nVacancyState.value = firstThree
            }

        }

    }
    fun deletIsFavorit(vacacyId: String){
        viewModelScope.launch {
            try {
                dataRepository.deletIsFavorit(vacacyId)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun setIsFavorit(vacacyId: String){
        viewModelScope.launch {
            try {
                dataRepository.setIsFavorit(vacacyId)

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun setDetailVacancy(vacancy: Vacancy){
     //   val _vacancyState = MutableStateFlow<List<Vacancy>>(emptyList())
        viewModelScope.launch {
            val firstThree = mutableListOf<Vacancy>()
            firstThree.add(vacancy)
            _vacancyDetail.value = firstThree
        }
    }

    fun openLinkOffer(context: Context, url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url) )
        if (intent.resolveActivity(context.packageManager) != null){
            context.startActivity(intent)
        }
    }
    fun openDialog(){
        viewModelScope.launch {
            stateDialog.value = true
        }
    }
    fun openDialogTwo(){
        viewModelScope.launch {
            stateDialogTwo.value = true
        }
    }
    fun closeDialog(){
        viewModelScope.launch {
           stateDialog.value = false
        }
    }
    fun closeDialogTwo(){
        viewModelScope.launch {
            stateDialogTwo.value = false
        }
    }



}