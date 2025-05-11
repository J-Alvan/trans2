package com.example.swifttrans.models


import androidx.lifecycle.ViewModel
import com.example.swifttrans.data.RouteViewModel
import com.example.swifttrans.ui.theme.screens.route.Route

class Routes : ViewModel() {
    private val repository = RouteViewModel()

    fun getRouteById(routeId: String): Route? {
        return repository.getRouteById(routeId)
    }

    fun getAllRoutes(): List<Route> {
        return repository.getAllRoutes()
    }
}
