package com.example.toktok.data

import java.io.Serializable

data class Store(
    var title: String?,
    var type: Int?,
    var distance: String?,
    var maxDiscount: Int?,
    var minDiscount: Int?,
    var location: String?
) : Serializable {
}
