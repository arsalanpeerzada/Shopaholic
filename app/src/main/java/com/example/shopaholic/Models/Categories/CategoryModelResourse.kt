package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class CategoryModelResourse(
    @SerializedName("get_category")
    val categoryList: ArrayList<CategoryModel>
)
