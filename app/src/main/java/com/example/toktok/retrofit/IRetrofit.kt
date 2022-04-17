package com.example.toktok.retrofit

import com.example.toktok.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface IRetrofit {
    @GET(API.GET_STORE_LIST)
    fun getStoreList() : Call<JsonElement>
}
