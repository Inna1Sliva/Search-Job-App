package com.it.shka.searchjobapp.koin

import com.it.shka.data.ImplDataRepository
import com.it.shka.data.ImplUserAuthRepository
import com.it.shka.domain.DataRepository
import com.it.shka.searchjobapp.viewmodel.DataViewModel
import com.it.shka.searchjobapp.viewmodel.UserAuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    single<DataRepository>{ ImplDataRepository(get()) }
    single <ImplUserAuthRepository>{ ImplUserAuthRepository() }

    viewModel{ DataViewModel(get()) }
    viewModel{ UserAuthViewModel(get()) }

}