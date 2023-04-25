package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Get_Dynamic_SaleModel(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title_id")
    var title_id: String? = null,
    @SerializedName("pro_dis_per")
    var pro_dis_per: String? = null,
    @SerializedName("pro_image")
    var pro_image: String? = null,
    @SerializedName("pro_title")
    var pro_title: String? = null,
    @SerializedName("pro_size")
    var pro_size: String? = null,
    @SerializedName("pro_before_dis")
    var pro_before_dis: String? = null,
    @SerializedName("pro_after_dis")
    var pro_after_dis: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete_status: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)
