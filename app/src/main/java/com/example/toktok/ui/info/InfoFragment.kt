package com.example.toktok.ui.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.toktok.MainActivity
import com.example.toktok.databinding.FragmentInfoBinding
import com.example.toktok.retrofit.RetrofitManager.Companion.loginTokenInfo
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment : Fragment() {
    private lateinit var listener: OnItemClickListener
    private var _binding: FragmentInfoBinding? = null

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
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPref = activity?.getSharedPreferences("KEY_DATA_TOKEN", Context.MODE_PRIVATE)
        root.ll_logout.setOnClickListener {
            loginTokenInfo = ""
            with(sharedPref!!.edit()) {
                remove("KEY_DATA_TOKEN")
                commit()
            }
            listener.onClick(root.ll_logout)
        }
        return root
    }

    open fun setOnItemClickListener(l: OnItemClickListener) {
        listener = l
    }

    interface OnItemClickListener {
        fun onClick(v: View)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}