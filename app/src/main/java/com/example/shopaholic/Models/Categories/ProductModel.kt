package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("cat_id")
    var cat_id: String? = null,
    @SerializedName("sub_id")
    var sub_id: String? = null,
    @SerializedName("brands")
    var brands: String? = null,
    @SerializedName("pro_name")
    var pro_name: String? = null,
    @SerializedName("pro_image")
    var pro_image: String? = null,

    @SerializedName("pro_title")
    var pro_title: String? = null,

    @SerializedName("pro_color")
    var pro_color: String? = null,

    @SerializedName("pro_quantity")
    var pro_quantity: String? = null,

    @SerializedName("pro_price")
    var pro_price: String? = null,

    @SerializedName("pro_size")
    var pro_size: String? = null,

    @SerializedName("pro_discount")
    var pro_discount: String? = null,

    @SerializedName("dis_offer_valid")
    var dis_offer_valid: String? = null,

    @SerializedName("pro_description")
    var pro_description: String? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("delete_status")
    var delete_status: String? = null,

    @SerializedName("created_at")
    var created_at: String? = null,
)
