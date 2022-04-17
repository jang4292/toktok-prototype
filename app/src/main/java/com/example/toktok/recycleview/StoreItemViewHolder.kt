package com.example.toktok.recycleview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.toktok.R
import com.example.toktok.data.Store
import com.example.toktok.utils.Constants.TAG
import kotlinx.android.synthetic.main.layout_store_item.view.*
import java.util.*

class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // 뷰들을 가져온다.
    private val storeImageView = itemView.iv_thumbnail
    private val storeTitle = itemView.tv_title
    private val storeType = itemView.tv_type
    private val storeDescription = itemView.tv_description
    private val storeDiscount = itemView.tv_discount

    private val imageResources =
        arrayOf(R.drawable.sample_store_image0, R.drawable.sample_store_image1);

    // 데이터와 뷰를 묶는다.
    fun bindWithView(storeItem: Store) {
        Log.d(TAG, "StoreItemViewHolder - bindWithView() called")

        itemView.setOnClickListener {

        }
        val num = Random().nextInt(2)
        storeImageView.setImageResource(imageResources[num])


        if (storeItem.title != null) {
            storeTitle.text = storeItem.title
        }
        if (storeItem.type != null) {
            when (storeItem.type) {
                0 -> {
                    storeType.text = "카페 / 디저트"
                }
                1 -> {
                    storeType.text = "과일 / 생산품"
                }
            }
        }
        var descriptionText = "";
        if (storeItem.location != null) {
            descriptionText += storeItem.location
        }
        if (storeItem.distance != null) {
            descriptionText += storeItem.distance
        }
        storeDescription.text = descriptionText

        if (storeItem.maxDiscount == storeItem.minDiscount) {
            storeDiscount.text = "${storeItem.minDiscount.toString()}% 할인"
        } else {
            storeDiscount.text = "${storeItem.minDiscount.toString()} ~ ${storeItem.maxDiscount}% 할인"
        }
    }
}
