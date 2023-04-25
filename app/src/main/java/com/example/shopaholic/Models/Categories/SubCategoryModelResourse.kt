package com.example.shopaholic.Models.Categories

import com.google.gson.annotations.SerializedName

data class SubCategoryModelResourse(
    @SerializedName("get_sub_category")
    val getsubCategoryModel: ArrayList<SubCategoryModel>
)
