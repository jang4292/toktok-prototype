package com.example.toktok.retrofit

import com.example.toktok.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IRetrofit {
    @GET(API.GET_PRODUCT_LIST)
    fun getProductList(): Call<JsonElement>

    @Headers("content-type: application/json")
    @POST(API.POST_SIGN_UP)
    fun postSiginUp(@Body param: HashMap<String, String>): Call<JsonElement>

    @Headers("content-type: application/json")
    @POST(API.POST_SIGN_IN)
    fun postSiginIn(@Body param: HashMap<String, String>): Call<JsonElement>
}
