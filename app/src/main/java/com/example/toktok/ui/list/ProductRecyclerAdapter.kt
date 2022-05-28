package com.example.toktok.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toktok.R

class ProductRecyclerAdapter(productRecyclerviewInterface: ProductRecyclerviewInterface) :
    RecyclerView.Adapter<ProductViewHolder>() {

    val TAG: String = "로그"

    //    private var modelList = ArrayList<MyModel>()
    private var productDataList = ArrayList<ProductData>()

    private var productRecyclerviewInterface: ProductRecyclerviewInterface? = null

    // 생성자
    init {
        this.productRecyclerviewInterface = productRecyclerviewInterface
    }

    // 뷰홀더가 생성 되었을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        // 연결할 레이아웃 설정

        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product_list_item, parent, false),
            this.productRecyclerviewInterface!!
        )
    }

    // 목록의 아이템수
    override fun getItemCount(): Int {
        return this.productDataList.size
    }

    // 뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder() called / position: $position")
        holder.bind(this.productDataList[position])

    }

    // 외부에서 데이터 넘기기
    fun submitList(productDataList: ArrayList<ProductData>) {
        this.productDataList = productDataList
        notifyDataSetChanged()
    }
}