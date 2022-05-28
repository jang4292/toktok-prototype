package com.example.toktok.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toktok.R

class FilterRecyclerAdapter(filterRecyclerviewInterface: FilterRecyclerviewInterface) :
    RecyclerView.Adapter<FilterViewHolder>() {

    val TAG: String = "로그"

    private var filterDataList = ArrayList<String>()

    private var filterRecyclerviewInterface: FilterRecyclerviewInterface? = null

    // 생성자
    init {
        this.filterRecyclerviewInterface = filterRecyclerviewInterface
    }

    // 뷰홀더가 생성 되었을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {

        // 연결할 레이아웃 설정

        return FilterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product_filter_item, parent, false),
            this.filterRecyclerviewInterface!!
        )
    }

    // 목록의 아이템수
    override fun getItemCount(): Int {
        return this.filterDataList.size
    }

    // 뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder() called / position: $position")
        holder.bind(this.filterDataList[position])

    }

    // 외부에서 데이터 넘기기
    fun submitList(filterDataList: ArrayList<String>) {
        this.filterDataList = filterDataList
        notifyDataSetChanged()
    }
}