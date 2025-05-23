package com.it.shka.domain


interface UserAuthRepository {
    fun isValidEmail(email: String): Boolean
    fun generateCode(): String
}