package com.example.pawningsystem.models

data class UserModel(
    var name: String? = null,
    var email: String? = null,
    var nic: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var username: String? = null,
    var pass: String? = null,
    var repass: String? = null,
){}