package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Brand_Model(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("brands")
    var brands: String? = null,
    @SerializedName("brands_image")
    var brands_image: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete_status: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)
