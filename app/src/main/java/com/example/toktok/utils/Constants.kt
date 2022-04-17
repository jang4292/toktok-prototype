package com.example.toktok.utils

object Constants {
    const val TAG: String = "로그"
}

enum class RESPONSE_STATUS {
    OKAY,
    FAIL,
    NO_CONTENT
}


object API {
    const val BASE_URL: String = "http://yhjang1.shop:3000/"
    const val GET_STORE_LIST : String = "storeList"
}
