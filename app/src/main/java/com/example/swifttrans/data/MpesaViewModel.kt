package com.example.swifttrans.data
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.swifttrans.BuildConfig
//import com.example.swifttrans.api.MpesaService
//import com.example.swifttrans.models.AccessToken
//import com.example.swifttrans.models.MpesaRequest
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import java.text.SimpleDateFormat
//import java.util.Base64
//import java.util.Date
//import java.util.Locale
//import javax.inject.Inject
//
//@HiltViewModel
//class MpesaViewModel @Inject constructor(
//    private val mpesaService: MpesaService
//) : ViewModel() {
//
//    var paymentState by mutableStateOf<PaymentState>(PaymentState.Idle)
//        private set
//
//    fun initiatePayment(phoneNumber: String, amount: String) {
//        if (phoneNumber.isBlank() || amount.isBlank()) {
//            paymentState = PaymentState.Error("Phone number and amount are required")
//            return
//        }
//
//        paymentState = PaymentState.Loading
//        viewModelScope.launch {
//            try {
//                // Step 1: Get Access Token
//                val tokenResponse = mpesaService.getAccessToken()
//                if (!tokenResponse.isSuccessful) {
//                    paymentState = PaymentState.Error("Failed to get access token")
//                    return@launch
//                }
//
//                val accessToken = tokenResponse.body()?.access_token ?: run {
//                    paymentState = PaymentState.Error("Access token is null")
//                    return@launch
//                }
//
//                // Step 2: Prepare STK Push Request
//                val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
//                val password = Base64.getEncoder().encodeToString(
//                    "${BuildConfig.MPESA_SHORTCODE}${BuildConfig.MPESA_PASSKEY}$timestamp".toByteArray()
//                )
//
//                val stkPushRequest = MpesaRequest(
//                    BusinessShortCode = BuildConfig.MPESA_SHORTCODE,
//                    Password = password,
//                    Timestamp = timestamp,
//                    TransactionType = "CustomerPayBillOnline",
//                    PartyA = phoneNumber,
//                    PartyB = BuildConfig.MPESA_SHORTCODE,
//                    Amount = amount,
//                    PartyB = BuildConfig.MPESA_SHORTCODE,
//                    PhoneNumber = phoneNumber,
//                    CallBackURL = BuildConfig.MPESA_CALLBACK_URL,
//                    AccountReference = "SwiftTrans",
//                    TransactionDesc = "Payment for SwiftTrans"
//                )
//
//                // Step 3: Initiate STK Push
//                val stkResponse = mpesaService.initiateStkPush(stkPushRequest)
//                if (stkResponse.isSuccessful) {
//                    paymentState = PaymentState.Success("Payment initiated. Check your phone.")
//                } else {
//                    paymentState = PaymentState.Error("Payment initiation failed: ${stkResponse.message()}")
//                }
//            } catch (e: Exception) {
//                paymentState = PaymentState.Error("Error: ${e.message}")
//            }
//        }
//    }
//}
//
//sealed class PaymentState {
//    object Idle : PaymentState()
//    object Loading : PaymentState()
//    data class Success(val message: String) : PaymentState()
//    data class Error(val message: String) : PaymentState()
//}