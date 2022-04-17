package com.example.toktok.data

import java.io.Serializable

data class Store(
    var index: Int,
    var title: String?,
    var type: String?,
    var description: String?,
    var discount: String?,
) : Serializable {
}
