package com.it.shka.searchjobapp.koin

import com.it.shka.data.ImplDataRepository
import com.it.shka.domain.DataRepository
import com.it.shka.searchjobapp.DataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    single<DataRepository>{ ImplDataRepository(get()) }

    viewModel{ DataViewModel(get()) }
}