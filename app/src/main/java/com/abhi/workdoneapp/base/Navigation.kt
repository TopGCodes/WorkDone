package com.abhi.workdoneapp.base

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object Search : Route()

    @Serializable
    data object TaskScreen : Route()

    @Serializable
    data object TaskCreateorUpdateScreen : Route()

    @Serializable
    data object SplashScreen : Route()
}




