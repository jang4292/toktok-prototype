package com.example.toktok.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.toktok.MainActivity
import com.example.toktok.databinding.FragmentStoreListBinding

class StoreListFragment_temp : Fragment() {

    private lateinit var mStoreListItem: ArrayList<StoreListItem>
    private var _binding: FragmentStoreListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreListBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        var mRecyclerView = binding.recyclerView
//
//        /* initiate adapter */
//        var mRecyclerAdapter = MyRecyclerAdapter()
//
//        mRecyclerAdapter.setOnItemClickListener(object : MyRecyclerAdapter.OnItemClickListener {
//            override fun onItemClick(v: View, data: StoreListItem, pos: Int) {
//                Toast.makeText(mainActivity, "Text pos : $pos", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        /* initiate recyclerview */
//        mRecyclerView.adapter = mRecyclerAdapter
//        mRecyclerView.layoutManager = LinearLayoutManager(mainActivity)
//
//        /* adapt data */
//        mStoreListItem = ArrayList()
//        for (i in 1..10) {
//            if (i % 2 == 0) mStoreListItem.add(
//                StoreListItem(
//                    R.drawable.ic_launcher_background,
//                    i.toString() + "가게 이름",
//                    "업종" + i.toString() + "번 타입",
//                    i.toString() + "번 위치",
//                    i.toString() + "km 거리",
//                    i.toString() + "번 즐겨찾기"
//
//                )
//            ) else mStoreListItem.add(
//                StoreListItem(
//                    R.drawable.ic_launcher_background,
//                    i.toString() + "가게 이름",
//                    "업종" + i.toString() + "번 종류",
//                    i.toString() + "번 골목",
//                    i.toString() + "km반경",
//                    i.toString() + "번 즐겨찾기 여부"
//                )
//            )
//        }
//        mRecyclerAdapter.setStoreList(mStoreListItem)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}