package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Array_Get_Dis_ProductModel(

    @SerializedName("get_discount_product")
    var get_discount_product: ArrayList<Get_Dis_ProductModel>? = null,
)
