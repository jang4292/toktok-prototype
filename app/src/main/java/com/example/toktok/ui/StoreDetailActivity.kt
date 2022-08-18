package com.example.toktok.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toktok.R
import com.example.toktok.databinding.ActivitySigninBinding
import com.example.toktok.databinding.ActivityStoreDetailBinding
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.retrofit.RetrofitManager.Companion.loginTokenInfo
import com.example.toktok.ui.CustomLoadingDialog
import com.example.toktok.utils.NAVI_BOTTOM_TYPE
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_store_detail.*

class StoreDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoreDetailBinding
//    private var isOnInformationView = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        tv_more.setOnClickListener {
            val lp = cl_information.layoutParams
            lp.height = resources.getDimension(R.dimen.information_height_on).toInt()
            tv_more.visibility = View.GONE
            btn_fold.visibility = View.VISIBLE
            cl_information.layoutParams = lp
        }
        btn_fold.setOnClickListener {
            val lp = cl_information.layoutParams
            lp.height = resources.getDimension(R.dimen.information_height_off).toInt()
            tv_more.visibility = View.VISIBLE
            btn_fold.visibility = View.GONE
            cl_information.layoutParams = lp
        }
    }
}