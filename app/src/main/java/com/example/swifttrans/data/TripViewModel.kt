package com.example.swifttrans.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swifttrans.models.PaymentState
import com.example.swifttrans.models.Trip
import com.example.swifttrans.repository.MPesaRepository
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class TripViewModel : ViewModel() {
    private val dbRef = FirebaseDatabase.getInstance().getReference("trips")
    private val _tripList = MutableStateFlow<List<Trip>>(emptyList())
    val tripList: StateFlow<List<Trip>> = _tripList

    init {
        fetchTrips()
    }

    fun saveTrip(trip: Trip) {
        val tripId = dbRef.push().key ?: UUID.randomUUID().toString()
        val newTrip = trip.copy(tripId = tripId)
        dbRef.child(tripId).setValue(newTrip)
    }

    fun fetchTrips() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val trips = mutableListOf<Trip>()
                for (tripSnapshot in snapshot.children) {
                    val trip = tripSnapshot.getValue(Trip::class.java)
                    trip?.let { trips.add(it) }
                }
                _tripList.value = trips
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if needed
            }
        })
    }

    fun deleteTrip(tripId: String) {
        dbRef.child(tripId).removeValue()
    }

    fun updateTrip(updatedTrip: Trip) {
        updatedTrip.tripId.let { tripId ->
            dbRef.child(tripId).setValue(updatedTrip)
        }
    }


    private val mpesaRepository = MPesaRepository()

    private val _paymentState = MutableStateFlow(PaymentState())
    val paymentState: StateFlow<PaymentState> = _paymentState.asStateFlow()

    // Payment method to be called from your UI
    fun initiatePayment(phone: String, amount: Double, reference: String, description: String) {
        viewModelScope.launch {
            // Update UI state to show loading
            _paymentState.update { it.copy(isLoading = true) }

            // Call the repository to initiate payment
            val result = mpesaRepository.initiateSTKPush(phone, amount, reference, description)

            result.fold(
                onSuccess = { response ->
                    // Payment request sent successfully
                    _paymentState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            paymentStatus = "Payment request sent to your phone. Please enter M-Pesa PIN to complete.",
                            errorMessage = ""
                        )
                    }

                    // Save payment information to your data store if needed
                    savePaymentInfo(
                        PaymentInfo(
                            transactionId = response.checkoutRequestID ?: "",
                            amount = amount,
                            phoneNumber = phone,
                            status = "Pending",
                            timestamp = System.currentTimeMillis()
                        )
                    )
                },
                onFailure = { exception ->
                    // Payment request failed
                    _paymentState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            paymentStatus = "Payment failed",
                            errorMessage = exception.message ?: "Unknown error occurred"
                        )
                    }
                }
            )
        }
    }

    // Method to save payment information to your data store
    private fun savePaymentInfo(paymentInfo: PaymentInfo) {
        // Implement according to your data storage strategy
        // This could be Firebase, Room database, or other storage mechanism
    }
}

