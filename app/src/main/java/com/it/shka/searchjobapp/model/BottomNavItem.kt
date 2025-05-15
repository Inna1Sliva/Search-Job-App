package com.it.shka.searchjobapp.model

import kotlinx.serialization.Serializable

@Serializable
data class BottomNavItem(
    val icon: Int,
    val title: String
)
