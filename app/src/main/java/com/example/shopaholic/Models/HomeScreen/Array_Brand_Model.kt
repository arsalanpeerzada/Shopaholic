package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Array_Brand_Model(

    @SerializedName("get_brands")
    var get_brands: ArrayList<Brand_Model>? = null,
)
