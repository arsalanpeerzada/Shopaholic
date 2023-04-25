package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Sale_Offer_Model(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title_name")
    var title_name: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete_status: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)
