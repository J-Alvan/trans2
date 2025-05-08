package com.example.swifttrans.api

import com.example.swifttrans.models.AccessToken
import com.example.swifttrans.models.MpesaRequest
import com.example.swifttrans.models.MpesaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MpesaService {
    @GET("oauth/v1/generate")
    suspend fun getAccessToken(
        @Query("grant_type") grantType: String = "client_credentials"
    ): Response<AccessToken>

    @POST("mpesa/stkpush/v1/processrequest")
    suspend fun initiateStkPush(
        @Body request: MpesaRequest
    ): Response<MpesaResponse>
}