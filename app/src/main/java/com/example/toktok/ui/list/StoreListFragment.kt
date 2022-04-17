package com.example.toktok.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toktok.MainActivity
import com.example.toktok.databinding.FragmentStoreListBinding
import com.example.toktok.recycleview.StoreGridRecyeclerViewAdapter
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.utils.Constants.TAG
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.fragment_store_list.view.*

class StoreListFragment : Fragment() {
    private var _binding: FragmentStoreListBinding? = null

    // 어답터
    private lateinit var storeGridRecyeclerViewAdapter: StoreGridRecyeclerViewAdapter

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

        RetrofitManager.instance.getStoreList(onCompleteListener = { responseState, responseDataArrayList ->
            when (responseState) {
                RESPONSE_STATUS.OKAY -> {
                    Log.d(TAG, "api 호출 성공 : ${responseDataArrayList?.size}")
                    this.storeGridRecyeclerViewAdapter.submitList(responseDataArrayList!!)

                }
                RESPONSE_STATUS.FAIL -> {
                    Toast.makeText(activity, "api 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "api 호출 실패 : $responseDataArrayList")
                }

                RESPONSE_STATUS.NO_CONTENT -> {
                    Toast.makeText(activity, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
        this.storeGridRecyeclerViewAdapter = StoreGridRecyeclerViewAdapter()
        root.my_store_recycler_view.layoutManager = GridLayoutManager(
            mainActivity as Context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        root.my_store_recycler_view.adapter = this.storeGridRecyeclerViewAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}