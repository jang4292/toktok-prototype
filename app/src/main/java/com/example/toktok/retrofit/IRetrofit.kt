package com.example.toktok.retrofit

import com.example.toktok.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {
    @GET(API.GET_PRODUCT_LIST)
    fun getProductList(): Call<JsonElement>

    @Headers("content-type: application/json")
    @POST(API.POST_SIGN_UP)
    fun postSiginUp(@Body param: HashMap<String, String>): Call<JsonElement>

    @Headers("content-type: application/json")
    @POST(API.POST_USER_LOGIN)
    fun postUserLogin(@Body param: HashMap<String, String>): Call<JsonElement>

    @Headers("content-type: application/json")
    @POST(API.POST_PURCHASE_PRODUCT)
    fun postPurchaseProduct(
        @Header("Authorization") Authorization: String,
        @Body param: HashMap<String, String>
    ): Call<JsonElement>
}
