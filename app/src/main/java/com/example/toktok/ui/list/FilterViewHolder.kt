package com.example.toktok.ui.list

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_product_filter_item.view.*

// 커스텀 뷰홀더
class FilterViewHolder(
    itemView: View,
    recyclerviewInterface: FilterRecyclerviewInterface
) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val TAG: String = "로그"

    private val filterName = itemView.tv_filter_name

    private var filterRecyclerviewInterface: FilterRecyclerviewInterface? = null

    // 기본 생성자
    init {
        Log.d(TAG, "MyViewHolder - init() called")

        itemView.setOnClickListener(this)
        this.filterRecyclerviewInterface = recyclerviewInterface
    }

    // 데이터와 뷰를 묶는다.
    fun bind(filterData: String) {
        Log.d(TAG, "MyViewHolder - bind() called")
        if (filterName != null) {
            filterName.text = filterData;
        }
    }

    override fun onClick(p0: View?) {
        Log.d(TAG, "MyViewHolder - onClick() called")
        this.filterRecyclerviewInterface?.onItemClicked(adapterPosition)
    }
}