package com.it.shka.searchjobapp.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.it.shka.data.ImplUserAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserAuthViewModel(private val userAuthRepository: ImplUserAuthRepository): ViewModel() {
    private val _codeState = MutableStateFlow<String>("")
    val codeState: StateFlow<String> = _codeState

    private val _emailState = MutableStateFlow<String>("")
    val emailState: StateFlow<String> = _emailState

    fun isValidEmailUser(email: String, context: Context){
        val code = userAuthRepository.generateCode()
        if (userAuthRepository.isValidEmail(email)){
            _emailState.value = email
            _codeState.value = code
            Log.d("Validate", "${code}")
            sendEmail(context, email, code)
        }else{
            Log.d("Validate", "Error")
        }

    }
    fun sendEmail(context: Context,email: String, code: String){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            //Intent.setData = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, "Ваш код подтверждения")
            putExtra(Intent.EXTRA_TEXT, "Ваш код: $code")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Log.d("Validate", "Error not program")        }
    }
}