package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class BannerModel(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("banner_img")
    var banner_img: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete_status: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)
