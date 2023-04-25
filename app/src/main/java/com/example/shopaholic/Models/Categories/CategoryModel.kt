package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("cat_name")
    var category: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)
