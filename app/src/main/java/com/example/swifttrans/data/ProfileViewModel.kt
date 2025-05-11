package com.example.swifttrans.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Data class to represent the user profile
data class Profile(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val imageUrl: String = ""
)

class ProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    private val _fetchuserProfile = MutableStateFlow(Profile())
    val fetchuserProfile: StateFlow<Profile> = _fetchuserProfile

    init {
        fetchProfile()
    }

    fun fetchProfile() {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            database.child("users").child(userId).get().addOnSuccessListener { snapshot ->
                val profile = snapshot.getValue(Profile::class.java)
                profile?.let {
                    _fetchuserProfile.value = it
                }
            }
        }
    }

    fun updateProfile(name: String, email: String, phone: String) {
        val userId = auth.currentUser?.uid ?: return
        val updatedProfile = Profile(name, email, phone)
        viewModelScope.launch {
            database.child("users").child(userId).setValue(updatedProfile).addOnSuccessListener {
                _fetchuserProfile.value = updatedProfile
            }
        }
    }

}

