package com.example.swifttrans.models


data class Trip(
    var tripId: String = "",
    var name: String = "",
    var phone: String = "",
    var idNumber: String = "",
    var scheduleDate: String = "",
    var routeName: String = "",
    var pickup: String = "",
    var destination: String = "",
    var price: Double = 0.0
)
