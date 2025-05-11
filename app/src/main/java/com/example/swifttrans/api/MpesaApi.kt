package com.example.swifttrans.api

import com.example.swifttrans.models.AccessTokenResponse
import com.example.swifttrans.models.STKPushRequest
import com.example.swifttrans.models.STKPushResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MPesaApiService {
    @GET("oauth/v1/generate?grant_type=client_credentials")
    suspend fun getAccessToken(
        @Header("Authorization") authorization: String
    ): Response<AccessTokenResponse>

    @POST("mpesa/stkpush/v1/processrequest")
    suspend fun performSTKPush(
        @Header("Authorization") authorization: String,
        @Body stkPushRequest: STKPushRequest
    ): Response<STKPushResponse>
}

