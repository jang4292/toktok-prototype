package com.example.toktok.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toktok.MainActivity
import com.example.toktok.data.Store
import com.example.toktok.databinding.FragmentStoreListBinding
import com.example.toktok.recycleview.StoreGridRecyeclerViewAdapter
import kotlinx.android.synthetic.main.fragment_store_list.view.*

class StoreListFragment : Fragment() {
    private var _binding: FragmentStoreListBinding? = null
    // 데이터
    private var photoList = ArrayList<Store>()
    // 어답터
    private lateinit var photoGridRecyeclerViewAdapter: StoreGridRecyeclerViewAdapter

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

        photoList.add(Store("title0", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title1", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title2", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title3", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title4", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title5", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title6", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title7", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        photoList.add(Store("title8", "카페/디저트", "장충동 350m 수량많음", "3050할인"))
        this.photoGridRecyeclerViewAdapter = StoreGridRecyeclerViewAdapter()

        this.photoGridRecyeclerViewAdapter.submitList(photoList)

        root.my_photo_recycler_view.layoutManager = GridLayoutManager(
            mainActivity as Context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        root.my_photo_recycler_view.adapter = this.photoGridRecyeclerViewAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}