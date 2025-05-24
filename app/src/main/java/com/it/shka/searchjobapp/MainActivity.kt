package com.it.shka.searchjobapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.it.shka.searchjobapp.navigation.MainScreen
import com.it.shka.searchjobapp.rout.RouteMainContent
import com.it.shka.searchjobapp.rout.RouteMainScreen
import com.it.shka.searchjobapp.ui.theme.SearchJobAppTheme
import com.it.shka.searchjobapp.viewmodel.DataViewModel
import com.it.shka.searchjobapp.viewmodel.UserAuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vm: DataViewModel by viewModel<DataViewModel>()
    private val AuthVm: UserAuthViewModel by viewModel<UserAuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SearchJobAppTheme {
                MainScreen(vm,AuthVm, RouteMainScreen.MainScreen.route)
            }
        }
    }
}

