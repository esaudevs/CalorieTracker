package com.esaudev.calorytracker.navigation

import androidx.navigation.NavController
import com.esaudev.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(route = event.route)
}