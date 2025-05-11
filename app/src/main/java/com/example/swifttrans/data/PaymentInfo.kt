package com.example.swifttrans.data

data class PaymentInfo(
    val transactionId: String = "",
    val amount: Double = 0.0,
    val phoneNumber: String = "",
    val status: String = "Pending",
    val timestamp: Long = System.currentTimeMillis()
)