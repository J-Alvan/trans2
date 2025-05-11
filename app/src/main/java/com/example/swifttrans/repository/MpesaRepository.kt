package com.example.swifttrans.repository

import android.util.Base64
import com.example.swifttrans.api.MPesaApiService
import com.example.swifttrans.models.STKPushRequest
import com.example.swifttrans.models.STKPushResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class MPesaRepository {
    private val BASE_URL = "https://sandbox.safaricom.co.ke/" // Change to production URL when ready
    private val BUSINESS_SHORT_CODE = "174379" // Your business short code
    private val PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919" // Your passkey
    private val TRANSACTION_TYPE = "CustomerPayBillOnline"
    private val CALLBACK_URL = "https://yourdomain.com/callback" // Your callback URL

    private val CONSUMER_KEY = "m21DUZBz9i5fqf0Khdxujd5tfKRn40SGAAAGggI4qHrhBIJl" // Replace with your consumer key
    private val CONSUMER_SECRET = "Gi0nMzvCNHT8swZGG7BRndbrtZEAKCdRBI8mi72gXfGjGqzBJJXRMGM8A8FJcKQI" // Replace with your consumer secret

    private val apiService: MPesaApiService by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MPesaApiService::class.java)
    }

    private fun getTimestamp(): String {
        return SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
    }

    private fun generatePassword(timestamp: String): String {
        val dataToEncode = BUSINESS_SHORT_CODE + PASSKEY + timestamp
        return Base64.encodeToString(dataToEncode.toByteArray(), Base64.NO_WRAP)
    }

    private fun getAuthorizationHeader(): String {
        val credentials = "$CONSUMER_KEY:$CONSUMER_SECRET"
        val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        return "Basic $encodedCredentials"
    }

    suspend fun initiateSTKPush(
        phoneNumber: String,
        amount: Double,
        accountReference: String,
        description: String
    ): Result<STKPushResponse> = withContext(Dispatchers.IO) {
        try {
            // Format phone number (remove leading 0 and add country code if needed)
            val formattedPhone = formatPhoneNumber(phoneNumber)

            // Step 1: Get access token
            val authHeader = getAuthorizationHeader()
            val tokenResponse = apiService.getAccessToken(authHeader)

            if (!tokenResponse.isSuccessful || tokenResponse.body() == null) {
                return@withContext Result.failure(Exception("Failed to get access token: ${tokenResponse.errorBody()?.string()}"))
            }

            val accessToken = tokenResponse.body()!!.accessToken

            // Step 2: Initiate STK Push
            val timestamp = getTimestamp()
            val password = generatePassword(timestamp)

            val stkRequest = STKPushRequest(
                businessShortCode = BUSINESS_SHORT_CODE,
                password = password,
                timestamp = timestamp,
                transactionType = TRANSACTION_TYPE,
                amount = amount.toInt().toString(), // M-Pesa API expects amount as string
                partyA = formattedPhone,
                partyB = BUSINESS_SHORT_CODE,
                phoneNumber = formattedPhone,
                callBackURL = CALLBACK_URL,
                accountReference = accountReference,
                transactionDesc = description
            )

            val stkResponse = apiService.performSTKPush("Bearer $accessToken", stkRequest)

            if (stkResponse.isSuccessful && stkResponse.body() != null) {
                Result.success(stkResponse.body()!!)
            } else {
                Result.failure(Exception("STK Push failed: ${stkResponse.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        // Remove any spaces, dashes, etc.
        var cleaned = phoneNumber.replace(Regex("[^0-9]"), "")

        // Remove leading zero if present
        if (cleaned.startsWith("0")) {
            cleaned = cleaned.substring(1)
        }

        // Add Kenya country code if not present
        if (!cleaned.startsWith("254")) {
            cleaned = "254$cleaned"
        }

        return cleaned
    }
}

