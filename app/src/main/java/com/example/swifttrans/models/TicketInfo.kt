package com.example.swifttrans.models

data class TicketInfo(
    val name: String,
    val route: String,
    val date: String,
    val phone: String,
    val amountPaid: String,
    val paymentMethod: String
)