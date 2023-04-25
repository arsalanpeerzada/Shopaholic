package com.example.shopaholic.Models.HomeScreen

import com.google.gson.annotations.SerializedName

data class Get_Daily_Essential(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("main_title_image")
    var main_title_image: String? = null,
    @SerializedName("main_title")
    var main_title: String? = null,
    @SerializedName("title_one")
    var title_one: String? = null,
    @SerializedName("image_one")
    var image_one: String? = null,
    @SerializedName("title_two")
    var title_two: String? = null,
    @SerializedName("image_two")
    var image_two: String? = null,
    @SerializedName("title_three")
    var title_three: String? = null,
    @SerializedName("image_three")
    var image_three: String? = null,
    @SerializedName("title_four")
    var title_four: String? = null,
    @SerializedName("image_four")
    var image_four: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("delete_status")
    var delete_status: String? = null,
    @SerializedName("created_at")
    var created_at: String? = null
)

