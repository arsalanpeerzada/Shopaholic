package com.mrusmansiddique.gpt_android.api

import com.example.shopaholic.Models.Categories.CategoryModelResourse
import com.example.shopaholic.Models.Categories.ProductModelResource
import com.example.shopaholic.Models.Categories.SubCategoryModelResourse
import com.example.shopaholic.Models.HomeScreen.*
import com.example.shopaholic.Models.Login.LoginResponseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface WebService {


    //    @POST("mark_as_read")
//    fun markAsRead(
//        @Header("token") token :String
//    ) :Call<newNotificationModel>

    @GET("get-banner")
    fun getBanners()
            : Call<Array_BannerModel>

    @GET("get-discount-product")
    fun get_discount_product()
            : Call<Array_Get_Dis_ProductModel>

    @GET("get-product-portfolio")
    fun get_product_portfolio()
            : Call<Array_Get_PortfolioModel>

    @GET("get-brands")
    fun get_brands()
            : Call<Array_Brand_Model>

    @GET("get-daily-essentails")
    fun get_daily_essential()
            : Call<Array_Get_Daily_Essential>

    @GET("get-dynamic-sales-offers")
    fun get_dynamic_sale()
            : Call<Sale_Offer_Model>

    @GET("get-sales-offer-title")
    fun get_sale_offer()
            : Call<Sale_Offer_Model>

    @FormUrlEncoded
    @POST("user-login")
    fun Login(
        @Field("phone_number") phoneNumer: String
    ): Call<LoginResponseModel>

    @FormUrlEncoded
    @POST("verify-otp")
    fun VerifyOtp(
        @Field("phone_number") phoneNumer: String,
        @Field("otp_code") otp_code: String
    ): Call<LoginResponseModel>

    @GET("get-category")
    fun getCategory(
    ): Call<CategoryModelResourse>


    @GET("get-sub-category")
    fun get_SubCategory(
    ): Call<SubCategoryModelResourse>


    @GET("get-products")
    fun get_Product(
    ): Call<ProductModelResource>


}