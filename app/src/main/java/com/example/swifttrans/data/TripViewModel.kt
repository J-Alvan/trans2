package com.example.swifttrans.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swifttrans.models.Trip
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TripViewModel : ViewModel() {

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips

    private val dbRef = FirebaseDatabase.getInstance().getReference("trips")

    init {
        fetchTrips()
    }

    fun saveTrip(trip: Trip) {
        val key = dbRef.push().key ?: return
        dbRef.child(key).setValue(trip)
    }

    private fun fetchTrips() {
        dbRef.get().addOnSuccessListener { snapshot ->
            val list = snapshot.children.mapNotNull { it.getValue(Trip::class.java) }
            viewModelScope.launch {
                _trips.emit(list)
            }
        }
    }
}
