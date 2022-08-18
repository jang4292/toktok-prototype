package com.example.toktok.ui.info

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toktok.databinding.ActivitySignupBinding
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.ui.CustomLoadingDialog
import com.example.toktok.utils.Constants
import com.example.toktok.utils.RESPONSE_STATUS

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnSignUp.setOnClickListener {
            val loadingDialog = CustomLoadingDialog(this)
            loadingDialog.startLoadingProgress()
            val account = binding.etAccount
            if (account.length() > 8) {
                val password = binding.etPassword
                if (password.length() > 8) {
                    val checkPassword = binding.etPasswordCheck
                    if (password.text.contentEquals(checkPassword.text)) {
                        val data = HashMap<String, String>()
                        val id = account.text.toString()
                        val pw = password.text.toString()
                        data.put("login_id", id)
                        data.put("password", pw)

                        RetrofitManager.instance.postSignUp(
                            data = data,
                            onCompleteListener = { responseState ->
                                when (responseState) {
                                    RESPONSE_STATUS.OKAY -> {
                                        Log.d(Constants.TAG, "api 호출 성공 ")
                                        setResult(Activity.RESULT_OK)
                                        loadingDialog.dismiss()
                                        finish()

                                    }
                                    RESPONSE_STATUS.FAIL -> {
                                        loadingDialog.dismiss()
                                        Toast.makeText(this, "존재하는 계정입니다.", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            })
                    } else {
                        loadingDialog.dismiss()
                    }
                } else {
                    loadingDialog.dismiss()
                }
            } else {
                loadingDialog.dismiss()
            }
        }
    }
}