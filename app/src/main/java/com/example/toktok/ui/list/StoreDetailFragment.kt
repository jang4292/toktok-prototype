package com.example.toktok.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.toktok.MainActivity
import com.example.toktok.R
import com.example.toktok.data.Store
import com.example.toktok.databinding.FragmentStoreDetailBinding
import com.example.toktok.utils.Constants.TAG
import kotlinx.android.synthetic.main.fragment_store_detail.view.*

class StoreDetailFragment : Fragment() {
    private var _binding: FragmentStoreDetailBinding? = null

    private val imageResources =
        arrayOf(R.drawable.sample_store_image0, R.drawable.sample_store_image1);

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
        _binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var storeItem = arguments?.getSerializable("data") as Store
        var imageIndex = arguments?.getInt("imageIndex", 0) as Int

        root.tv_actionbar_title.text = storeItem.title
        root.iv_thumbnail.setImageResource(imageResources[imageIndex])

        if (storeItem.type != null) {
            when (storeItem.type) {
                0 -> {
                    root.tv_type.text = "카페 / 디저트"
                }
                1 -> {
                    root.tv_type.text = "과일 / 생산품"
                }
            }
        }
        var descriptionText = "";
        if (storeItem.location != null) {
            descriptionText += "${storeItem.location} "
        }
        if (storeItem.distance != null) {
            descriptionText += "${storeItem.distance}m"
        }
        root.tv_description.text = descriptionText

        if (storeItem.maxDiscount == storeItem.minDiscount) {
            root.tv_discount.text = "${storeItem.minDiscount.toString()}% 할인"
        } else {
            root.tv_discount.text =
                "${storeItem.minDiscount.toString()} ~ ${storeItem.maxDiscount}% 할인"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}