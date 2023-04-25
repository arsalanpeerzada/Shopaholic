package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class ProductModelResource(
    @SerializedName("get_products")
    val productList: ArrayList<ProductModel>
)
