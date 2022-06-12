package com.example.toktok.ui.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.toktok.MainActivity
import com.example.toktok.databinding.FragmentInfoBinding
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.utils.Constants.TAG
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment : Fragment() {

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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val isLogined = sharedPref?.getBoolean("isLogined", false)

        root.sign_in.setOnClickListener {
            val account = binding.etAccount
            val password = binding.etPassword
            val data = HashMap<String, String>()
            data.put("login_id", account.text.toString())
            data.put("password", password.text.toString())

            RetrofitManager.instance.postSignIn(
                data = data,
                onCompleteListener = { responseState ->
                    when (responseState) {
                        RESPONSE_STATUS.OKAY -> {
                            Log.d(TAG, "api 호출 성공 ")

                            with(sharedPref!!.edit()) {
                                putBoolean("isLogined", true)
                                apply()
                            }

                            root.ll_login_view.visibility = View.GONE
                            root.ll_info_view.visibility = View.VISIBLE
                        }
                        RESPONSE_STATUS.FAIL -> {

                        }
                    }
                })
        }
        root.btn_sign_up.setOnClickListener {
            activity?.let {
                val intent = Intent(it, SignUpActivity::class.java)
                startActivity(intent)
            }
        }


        root.ll_btn_logout.setOnClickListener {
            with(sharedPref!!.edit()) {
                putBoolean("isLogined", false)
                apply()
            }
            root.ll_login_view.visibility = View.VISIBLE
            root.ll_info_view.visibility = View.GONE
        }

        if (!isLogined!!) {
            root.ll_login_view.visibility = View.VISIBLE
            root.ll_info_view.visibility = View.GONE
        } else {
            root.ll_login_view.visibility = View.GONE
            root.ll_info_view.visibility = View.VISIBLE
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}