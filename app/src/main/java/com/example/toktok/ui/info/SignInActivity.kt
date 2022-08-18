package com.example.toktok.ui.info

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toktok.databinding.ActivitySigninBinding
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.retrofit.RetrofitManager.Companion.loginTokenInfo
import com.example.toktok.ui.CustomLoadingDialog
import com.example.toktok.utils.NAVI_BOTTOM_TYPE
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private val REQUEST_CODE = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val loadingDialog = CustomLoadingDialog(this)

        btn_sign_in.setOnClickListener {
            loadingDialog.startLoadingProgress()

            val account = binding.etAccount
            if (account.length() < 8 || account.length() > 15) {
                loadingDialog.dismiss()
                Toast.makeText(this, "ID 확인해주세요 길이 : " + account.length(), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val password = binding.etPassword
                val data = HashMap<String, String>()
                data.put("login_id", account.text.toString())
                data.put("password", password.text.toString())

                RetrofitManager.instance.postUserLogin(
                    data = data,
                    onCompleteListener = { responseState ->
                        when (responseState) {
                            RESPONSE_STATUS.OKAY -> {
                                Log.d("TEST", "api 호출 성공 ")
                                val sharedPref = getSharedPreferences("KEY_DATA_TOKEN", Context.MODE_PRIVATE)
                                with(sharedPref!!.edit()) {
                                    putString("KEY_DATA_TOKEN", loginTokenInfo)
                                    apply()
                                }

                                loadingDialog.dismiss()
                                setResult(Activity.RESULT_OK)
                                finish()
                            }
                            RESPONSE_STATUS.FAIL -> {
                                Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                                loadingDialog.dismiss()
                            }
                            RESPONSE_STATUS.ERROR -> {
                                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                                loadingDialog.dismiss()
                            }
                        }
                    })
            }
        }

        btn_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE) {
            if (resultCode === RESULT_OK) {
                val sharedPref = getSharedPreferences("KEY_DATA_TOKEN", Context.MODE_PRIVATE)
                with(sharedPref!!.edit()) {
                    putString("KEY_DATA_TOKEN", loginTokenInfo)
                    apply()
                }
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}