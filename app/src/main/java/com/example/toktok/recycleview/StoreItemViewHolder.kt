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
        Log.d(TAG, "PhotoItemViewHolder - bindWithView() called")

        itemView.setOnClickListener {

        }
        val num = Random().nextInt(2)
        storeImageView.setImageResource(imageResources[num])
        storeTitle.text = storeItem.title
        storeType.text = storeItem.type
        storeDescription.text = storeItem.description
        storeDiscount.text = storeItem.discount
    }


}
