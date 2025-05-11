package com.example.swifttrans.models

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: String
)

data class STKPushRequest(
    @SerializedName("BusinessShortCode") val businessShortCode: String,
    @SerializedName("Password") val password: String,
    @SerializedName("Timestamp") val timestamp: String,
    @SerializedName("TransactionType") val transactionType: String,
    @SerializedName("Amount") val amount: String,
    @SerializedName("PartyA") val partyA: String,
    @SerializedName("PartyB") val partyB: String,
    @SerializedName("PhoneNumber") val phoneNumber: String,
    @SerializedName("CallBackURL") val callBackURL: String,
    @SerializedName("AccountReference") val accountReference: String,
    @SerializedName("TransactionDesc") val transactionDesc: String
)

data class STKPushResponse(
    @SerializedName("MerchantRequestID") val merchantRequestID: String?,
    @SerializedName("CheckoutRequestID") val checkoutRequestID: String?,
    @SerializedName("ResponseCode") val responseCode: String?,
    @SerializedName("ResponseDescription") val responseDescription: String?,
    @SerializedName("CustomerMessage") val customerMessage: String?
)

// Payment state for UI updates
data class PaymentState(
    val isLoading: Boolean = false,
    val paymentStatus: String = "",
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)