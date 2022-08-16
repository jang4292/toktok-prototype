package com.example.toktok.ui.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.toktok.databinding.ActivitySigninBinding
import com.example.toktok.ui.CustomLoadingDialog

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

//        val loadingDialog = CustomLoadingDialog(this)
//        loadingDialog.startLoadingProgress()

//        binding.btnSignUp.setOnClickListener {
//            val account = binding.etAccount
//            if (account.length() > 8) {
//                val password = binding.etPassword
//                if (password.length() > 8) {
//                    val checkPassword = binding.etPasswordCheck
//                    if (password.text.contentEquals(checkPassword.text)) {
//                        val data = HashMap<String, String>()
//                        val id = account.text.toString()
//                        val pw = password.text.toString()
//                        data.put("login_id", id)
//                        data.put("password", pw)
//
//                        RetrofitManager.instance.postSignUp(
//                            data = data,
//                            onCompleteListener = { responseState ->
//                                when (responseState) {
//                                    RESPONSE_STATUS.OKAY -> {
//                                        Log.d(Constants.TAG, "api 호출 성공 ")
//                                        val intent = Intent()
//                                        intent.putExtra("id", id)
//                                        intent.putExtra("pw", pw)
//                                        setResult(Activity.RESULT_OK, intent)
//                                        loadingDialog.dismiss()
//                                        finish()
//
//                                    }
//                                    RESPONSE_STATUS.FAIL -> {
//                                        loadingDialog.dismiss()
//                                        Toast.makeText(this, "존재하는 계정입니다.", Toast.LENGTH_SHORT)
//                                            .show()
//                                    }
//                                }
//                            })
//                    }
//                }
//            }
//        }
    }
}