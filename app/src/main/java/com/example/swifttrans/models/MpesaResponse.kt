package com.example.swifttrans.models

data class MpesaResponse(
    val MerchantRequestID: String,
    val CheckoutRequestID: String,
    val ResponseCode: String,
    val ResponseDescription: String,
    val CustomerMessage: String
)