package com.it.shka.searchjobapp


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

    private   val _vacancyLive = MutableLiveData<List<Vacancy>>(emptyList())
  val vacancyLive : MutableLiveData<List<Vacancy>> = _vacancyLive

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
    fun setIsFavorit(vacacyId: String){
        viewModelScope.launch {
            try {
                dataRepository.setIsFavorit(vacacyId)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
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
                for (isFavorit in  vacancy){
                    if (isFavorit.favorite?.isNotEmpty()==true){
                        addFavorit.add(isFavorit)
                    }
                    _vacancyLive.postValue(addFavorit)
                    _favoritState.value = addFavorit
                }
            }

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