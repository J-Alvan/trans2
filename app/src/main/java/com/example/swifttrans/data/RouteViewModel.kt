package com.example.swifttrans.data

import com.example.swifttrans.ui.theme.screens.route.Route

class RouteViewModel {
    private val routes = listOf(
        Route("1", "Nairobi to Kisumu", "Nairobi CBD", "Kisumu Town", 1000.0,"7 hrs"),
        Route("2", "Kisumu to Kakamega", "Downtown Plaza", "University Campus", 900.0, "5 hrs"),
        Route("3", "Mombasa to Nairobi", "Harbor Point", "Nairobi CBD", 1200.0, "12hrs"),
        Route("4", "Nairobi to Meru", "Nairobi CBD", "Summit View", 600.0, "90 min"),
        Route("5", "Nakuru to Kirinyaga", "City Center", "Shopping District", 800.0, "25 min")
    )
    fun getRouteById(routeId: String): Route? {
        return routes.find { it.id == routeId }
    }

    fun getAllRoutes(): List<Route> {
        return routes
    }
}


