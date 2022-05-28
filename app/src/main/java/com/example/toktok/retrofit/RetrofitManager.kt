package com.example.toktok.retrofit

import android.util.Log
import com.example.toktok.ui.list.ProductData
import com.example.toktok.utils.API
import com.example.toktok.utils.Constants.TAG
import com.example.toktok.utils.RESPONSE_STATUS
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL_DEV)?.create(IRetrofit::class.java)

    fun getProductList(onCompleteListener: (RESPONSE_STATUS, ArrayList<ProductData>?) -> Unit) {
        val call = iRetrofit?.getProductList().let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                onCompleteListener(RESPONSE_STATUS.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / response : ${response.body()}")
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            Log.d(TAG, "data : ${it}")

                            var parsedStoreDataArray = ArrayList<ProductData>()
                            val body = it.asJsonObject
                            val results = body.getAsJsonArray("data")

                            if (results.size() > 0) {
                                results.forEach { resultItem ->
                                    val resultItemObject = resultItem.asJsonObject

                                    val idx = resultItemObject.get("idx").asInt
                                    val sellerIdx = resultItemObject.get("seller_idx").asInt
                                    val sellerName = resultItemObject.get("sellerName").asString
                                    val name = resultItemObject.get("name").asString
                                    val sellByDate = resultItemObject.get("sell_by_date").asString
                                    val price = resultItemObject.get("price").asInt
                                    val discountPrice = resultItemObject.get("discount_price").asInt
                                    val discountRate = resultItemObject.get("discount_rate").asInt
                                    val url = resultItemObject.get("url").asString

                                    val storeItem = ProductData(
                                        idx,
                                        sellerIdx,
                                        sellerName,
                                        name,
                                        sellByDate,
                                        price,
                                        discountPrice,
                                        discountRate,
                                        url
                                    )
                                    parsedStoreDataArray.add(storeItem)
                                }
                                onCompleteListener(RESPONSE_STATUS.OKAY, parsedStoreDataArray)
                            } else {
                                onCompleteListener(RESPONSE_STATUS.NO_CONTENT, null)
                            }
                        }
                    }
                }
            }
        })
    }

    fun postSignUp(data: HashMap<String, String>, onCompleteListener: (RESPONSE_STATUS) -> Unit) {
        val call = iRetrofit?.postSiginUp(data).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                onCompleteListener(RESPONSE_STATUS.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / response : ${response.body()}")
                when (response.code()) {
                    200 -> {
                        onCompleteListener(RESPONSE_STATUS.OKAY)
                    }
                    500 -> {
                        onCompleteListener(RESPONSE_STATUS.ERROR)
                    }
                }
            }
        })
    }

    fun postSignIn(data: HashMap<String, String>, onCompleteListener: (RESPONSE_STATUS) -> Unit) {
        val call = iRetrofit?.postSiginIn(data).let {
            it
        } ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                onCompleteListener(RESPONSE_STATUS.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / response : ${response.body()}")
                when (response.code()) {
                    200 -> {
                        onCompleteListener(RESPONSE_STATUS.OKAY)
                    }
                    500 -> {
                        onCompleteListener(RESPONSE_STATUS.ERROR)
                    }
                }
            }
        })
    }
}