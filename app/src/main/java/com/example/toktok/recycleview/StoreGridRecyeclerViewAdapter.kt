package com.example.toktok.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toktok.MainActivity
import com.example.toktok.R
import com.example.toktok.data.Store
import com.example.toktok.utils.Constants.TAG

class StoreGridRecyeclerViewAdapter constructor(private val activity: MainActivity) :
    RecyclerView.Adapter<StoreItemViewHolder>() {

    private var storeList = ArrayList<Store>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val storeItemViewHolder = StoreItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_store_item, parent, false)
        )
        return storeItemViewHolder
    }

    override fun getItemCount(): Int {
        return this.storeList.size
    }

    // 뷰가 묶였을때 데이터를 뷰홀더에 넘겨준다.
    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        holder.bindWithView(this.storeList[position])

        holder.itemView.setOnClickListener {
            Log.d(TAG, "onClick $position")

            val data = it.getTag(R.id.storeData)
            val imageIndex = it.getTag(R.id.imageIndex)

            val mainActivity = activity as MainActivity
            mainActivity.showStoreDetailFragment(data as Store, imageIndex as Int);
        }
    }

    // 외부에서 어답터에 데이터 배열을 넣어준다.
    fun submitList(storeList: ArrayList<Store>) {
        this.storeList = storeList
        notifyDataSetChanged()
    }
}
