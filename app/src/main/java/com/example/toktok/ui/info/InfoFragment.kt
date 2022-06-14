package com.example.toktok.ui.info

import android.app.Activity.RESULT_OK
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
import com.example.toktok.retrofit.RetrofitManager.Companion.loginTokenInfo
import com.example.toktok.utils.Constants.TAG
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment : Fragment() {
    private val REQUEST_CODE = 2000
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

        root.ll_login_view.visibility = View.VISIBLE
        root.ll_info_view.visibility = View.GONE

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        loginTokenInfo = sharedPref?.getString("KEY_DATA_TOKEN", "").toString()
        root.sign_in.setOnClickListener {
            val account = binding.etAccount
            val password = binding.etPassword
            val data = HashMap<String, String>()
            data.put("login_id", account.text.toString())
            data.put("password", password.text.toString())

            RetrofitManager.instance.postUserLogin(
                data = data,
                onCompleteListener = { responseState ->
                    when (responseState) {
                        RESPONSE_STATUS.OKAY -> {
                            Log.d(TAG, "api 호출 성공 ")

                            with(sharedPref!!.edit()) {
                                putString("KEY_DATA_TOKEN", loginTokenInfo)
                                apply()
                            }
                            refreshView()
                        }
                        RESPONSE_STATUS.FAIL -> {

                        }
                    }
                })
        }
        root.btn_sign_up.setOnClickListener {
            activity?.let {
                val intent = Intent(it, SignUpActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }

        root.ll_btn_logout.setOnClickListener {
            loginTokenInfo = "";
            with(sharedPref!!.edit()) {
                putString("KEY_DATA_TOKEN", loginTokenInfo)
                apply()
            }
            refreshView()
        }

        refreshView()

        return root
    }

    /**
     * Receive the result from a previous call to
     * [.startActivityForResult].  This follows the
     * related Activity API as described there in
     * [Activity.onActivityResult].
     *
     * @param requestCode The integer request code originally supplied to
     * startActivityForResult(), allowing you to identify who this
     * result came from.
     * @param resultCode The integer result code returned by the child activity
     * through its setResult().
     * @param data An Intent, which can return result data to the caller
     * (various data can be attached to Intent "extras").
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE) {
            if (resultCode === RESULT_OK) {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                with(sharedPref!!.edit()) {
                    putString("KEY_DATA_TOKEN", loginTokenInfo)
                    apply()
                }
                refreshView()
            }
        }
    }

    private fun refreshView() {
        if (loginTokenInfo == null || loginTokenInfo.length === 0) {
            binding.root.ll_login_view.visibility = View.VISIBLE
            binding.root.ll_info_view.visibility = View.GONE
        } else {
            binding.root.ll_login_view.visibility = View.GONE
            binding.root.ll_info_view.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}