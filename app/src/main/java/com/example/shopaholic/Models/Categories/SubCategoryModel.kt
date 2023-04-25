package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class SubCategoryModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("cat_id")
    var category: String? = null,
    @SerializedName("sub_cat_name")
    var category_Name: String? = null,
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("delete_status")
    var delete_status: String? = null,

    @SerializedName("created_at")
    var created_at: String? = null
)

