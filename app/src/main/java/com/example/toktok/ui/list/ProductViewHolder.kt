package com.example.toktok.ui.list

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.toktok.App
import kotlinx.android.synthetic.main.fragment_product_list_item.view.*

// 커스텀 뷰홀더
class ProductViewHolder(
    itemView: View,
    recyclerviewInterface: ProductRecyclerviewInterface
) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val TAG: String = "로그"

    private val profileImage = itemView.profile
    private val productName = itemView.product_name
    private val sellerName = itemView.store_name
    private val sellByDate = itemView.sell_by_date_text
    private val originPrice = itemView.origin_price_text
    private val discountedPrice = itemView.discount_price_text

    private var productRecyclerviewInterface: ProductRecyclerviewInterface? = null

    // 기본 생성자
    init {
        Log.d(TAG, "MyViewHolder - init() called")

        itemView.setOnClickListener(this)
        this.productRecyclerviewInterface = recyclerviewInterface
    }

    // 데이터와 뷰를 묶는다.
    fun bind(productData: ProductData) {
        Log.d(TAG, "MyViewHolder - bind() called")

        if (productName != null) {
            productName.text = productData.name;
        }

        if (sellerName != null) {
            sellerName.text = productData.sellerName
        }

        if (sellByDate != null) {
            sellByDate.text = productData.sellByDate
        }

        if (originPrice != null) {
            originPrice.text = productData.price.toString()
        }

        if (discountedPrice != null) {
            discountedPrice.text = productData.discountPrice.toString()
        }

        Glide
            .with(App.instance)
            .load(productData.url)
            .into(profileImage)
    }

    override fun onClick(p0: View?) {
        Log.d(TAG, "MyViewHolder - onClick() called")
        this.productRecyclerviewInterface?.onItemClicked(adapterPosition)
    }


}
