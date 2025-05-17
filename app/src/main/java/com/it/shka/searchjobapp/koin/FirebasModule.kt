package com.it.shka.searchjobapp.koin

import com.google.firebase.database.FirebaseDatabase
import com.it.shka.data.ImplDataRepository
import org.koin.dsl.module

val FirebasModule= module {
    single { ImplDataRepository(providesFirebaseDatabase()) }

}
fun providesFirebaseDatabase():FirebaseDatabase{
    return  FirebaseDatabase.getInstance()
}