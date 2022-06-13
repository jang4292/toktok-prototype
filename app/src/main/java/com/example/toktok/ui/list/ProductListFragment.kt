package com.example.toktok.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toktok.MainActivity
import com.example.toktok.R
import com.example.toktok.databinding.FragmentProductListBinding
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.fragment_product_list.view.*

class ProductListFragment : Fragment(), ProductRecyclerviewInterface, FilterRecyclerviewInterface {
    private var _binding: FragmentProductListBinding? = null

    val TAG: String = "로그"

    // 어답터
    private lateinit var recyclerAdapter: ProductRecyclerAdapter
    private lateinit var filterRecyclerAdapter: FilterRecyclerAdapter

    private lateinit var purchaseView: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity

    //    lateinit var context: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val root: View = binding.root


        Log.d(TAG, "MainActivity - onCreate() called")
        // 어답터 인스턴스 생성
        recyclerAdapter = ProductRecyclerAdapter(this)

        // 리사이클러뷰 설정
        root.product_recycler_view.apply {

            // 리사이클러뷰 방향 등 설정
            layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)

            // 어답터 장착
            adapter = recyclerAdapter
        }

        filterRecyclerAdapter = FilterRecyclerAdapter(this);

        root.filter_recycler_view.apply {

            // 리사이클러뷰 방향 등 설정
            layoutManager = LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false)

            // 어답터 장착
            adapter = filterRecyclerAdapter
        }

        val stringArray: ArrayList<String> = arrayListOf(
            "filter1",
            "filter2",
            "filter3",
            "filter4",
            "filter5",
            "filter6",
            "filter7",
            "filter8",
            "filter9",
            "filter10",
            "filter11",
            "filter12"
        )
        filterRecyclerAdapter.submitList(stringArray!!)

        refreshData()

        return root
    }

    fun refreshData() {
        RetrofitManager.instance.getProductList(onCompleteListener = { responseState, responseDataArrayList ->
            when (responseState) {
                RESPONSE_STATUS.OKAY -> {
                    Log.d(TAG, "api 호출 성공 : ${responseDataArrayList?.size}")
                    this.recyclerAdapter.submitList(responseDataArrayList!!)

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
    }

    override fun onItemClicked(position: Int) {
        Log.d(TAG, "MainActivity - onItemClicked() called / position: $position")

        val productData = this.recyclerAdapter.getItem(position)
        this.purchaseView.setTag(R.id.ids_key_login_token, productData.idx)
        this.purchaseView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setPurchasedView(layout: LinearLayout) {
        this.purchaseView = layout
    }
}