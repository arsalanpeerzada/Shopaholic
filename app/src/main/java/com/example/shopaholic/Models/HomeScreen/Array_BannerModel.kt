package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Array_BannerModel(

    @SerializedName("get_banner")
    var banner: ArrayList<BannerModel>? = null,
)
