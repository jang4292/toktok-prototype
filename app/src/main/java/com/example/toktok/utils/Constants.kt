package com.example.toktok.utils

object Constants {
    const val TAG: String = "로그"
}

enum class RESPONSE_STATUS {
    OKAY,
    FAIL,
    ERROR,
    NO_CONTENT
}

enum class NAVI_BOTTOM_TYPE {
    STORE,
    MAP,
    SEARCH,
    INFO
}


object API {
    const val BASE_URL: String = "http://yhjang1.shop:3000/"
    const val GET_STORE_LIST: String = "storeList"
    const val POST_SIGN_UP: String = "users/register"
    const val POST_SIGN_IN: String = "users/login"
}
