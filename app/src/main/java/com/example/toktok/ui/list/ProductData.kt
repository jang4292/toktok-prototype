package com.example.toktok.ui.list

import java.io.Serializable

data class ProductData(
    val idx: Int?,
    val sellerIdx: Int?,
    val sellerName: String?,
    val name: String?,
    val sellByDate: String?,
    val price: Int?,
    val discountPrice: Int?,
    val discountRate: Int?,
    val url: String?
) : Serializable