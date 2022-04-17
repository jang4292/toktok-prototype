package com.example.toktok.recycleview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toktok.MainActivity
import com.example.toktok.R
import com.example.toktok.data.Store
import com.example.toktok.ui.list.StoreDetailFragment
import com.example.toktok.utils.Constants.TAG
import java.io.Serializable

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
            val bundle = Bundle()
            bundle.putSerializable("data", data as Serializable)
            bundle.putInt("imageIndex", imageIndex as Int)

            val transaction = activity.supportFragmentManager.beginTransaction()
            val fragment = StoreDetailFragment();
            fragment.arguments = bundle
            transaction.replace(R.id.nav_host_fragment_activity_main, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    // 외부에서 어답터에 데이터 배열을 넣어준다.
    fun submitList(storeList: ArrayList<Store>) {
        this.storeList = storeList
        notifyDataSetChanged()
    }
}
