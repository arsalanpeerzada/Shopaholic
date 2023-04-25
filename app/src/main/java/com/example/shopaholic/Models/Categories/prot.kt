package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class prot(
    @SerializedName("Product_ID")
    var product_ID: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("quantitiy")
    var quantitiy: Int,
    @SerializedName("position")
    var position: Int
)
