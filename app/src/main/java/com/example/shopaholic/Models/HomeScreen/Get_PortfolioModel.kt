package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Get_PortfolioModel(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("main_product_title")
    var main_product_title: String? = null,
    @SerializedName("product_one")
    var product_one: String? = null,
    @SerializedName("product_one_image")
    var product_one_image: String? = null,
    @SerializedName("product_two")
    var product_two: String? = null,
    @SerializedName("product_two_image")
    var product_two_image: String? = null,
    @SerializedName("product_three")
    var product_three: String? = null,
    @SerializedName("product_three_image")
    var product_three_image: String? = null,
    @SerializedName("product_four")
    var product_four: String? = null,
    @SerializedName("product_four_image")
    var product_four_image: String? = null,
    @SerializedName("product_five")
    var product_five: String? = null,
    @SerializedName("product_five_image")
    var product_five_image: String? = null,
    @SerializedName("product_one_price")
    var product_one_price: String? = null,
    @SerializedName("product_two_price")
    var product_two_price: String? = null,
    @SerializedName("product_three_price")
    var product_three_price: String? = null,
    @SerializedName("product_four_price")
    var product_four_price: String? = null,
    @SerializedName("product_five_price")
    var product_five_price: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete_status: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)

