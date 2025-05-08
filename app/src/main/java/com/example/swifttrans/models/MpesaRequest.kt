package com.example.swifttrans.models

data class MpesaRequest(
    val phone: String,
    val amount: String,
    val callbackUrl: String,
    val accountReference: String,
    val transactionDesc: String,
    val businessShortCode: String,
    val password: String,
    val timestamp: String
)
