package com.example.shopaholic.Models.Login

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("user_id")
    var userId: String? = null,
    @SerializedName("otp_code")
    var otp: String? = null
)
