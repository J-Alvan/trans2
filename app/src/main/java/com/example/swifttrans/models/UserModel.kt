package com.example.swifttrans.models

data class UserModel(
    val imageUrl: String? = null,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var userId: String = ""
)