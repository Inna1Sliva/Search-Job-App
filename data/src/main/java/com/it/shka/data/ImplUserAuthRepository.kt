package com.it.shka.data

import com.google.firebase.database.core.Context
import com.it.shka.domain.UserAuthRepository

class ImplUserAuthRepository(): UserAuthRepository {


    override fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()    }

    override fun generateCode(): String {
        return (1000..9999).random().toString()
    }
}