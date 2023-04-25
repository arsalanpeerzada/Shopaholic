package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Array_Get_Daily_Essential(

    @SerializedName("get_daily_essentails")
    var get_daily_essentails: ArrayList<Get_Daily_Essential>? = null,
)

