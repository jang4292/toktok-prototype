package com.example.toktok.retrofit

import android.util.Log
import com.example.toktok.data.Store
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
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    fun getStoreList(onCompleteListener: (RESPONSE_STATUS, ArrayList<Store>?) -> Unit) {
        val call = iRetrofit?.getStoreList().let {
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

                            var parsedStoreDataArray = ArrayList<Store>()
                            val body = it.asJsonObject
                            val results = body.getAsJsonArray("data")

                            if (results.size() > 0) {
                                results.forEach { resultItem ->
                                    val resultItemObject = resultItem.asJsonObject
                                    val index = resultItemObject.get("idx").asInt
                                    val title: String = resultItemObject.get("name").asString
                                    val type: String = resultItemObject.get("store_type").asString
                                    val distance: String = resultItemObject.get("distance").asString

                                    val storeItem = Store(
                                        index,
                                        title,
                                        type,
                                        null,
                                        distance
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
}
