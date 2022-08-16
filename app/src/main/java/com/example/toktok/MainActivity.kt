package com.example.toktok

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.toktok.databinding.ActivityMainBinding
import com.example.toktok.retrofit.RetrofitManager
import com.example.toktok.ui.info.InfoFragment
import com.example.toktok.ui.info.SignInActivity
import com.example.toktok.ui.list.ProductListFragment
import com.example.toktok.ui.map.MapFragment
import com.example.toktok.ui.search.SearchFragment
import com.example.toktok.utils.NAVI_BOTTOM_TYPE
import com.example.toktok.utils.RESPONSE_STATUS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 3000
    private lateinit var productList: ProductListFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_DENIED) { //포그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }

        binding.ivClose.setOnClickListener {
            binding.llPurchaseView.visibility = View.GONE
        }

        binding.btnPurchase.setOnClickListener {
            val productId = binding.llPurchaseView.getTag(R.id.ids_key_login_token)

            if (productId == 0) {
                Toast.makeText(this, "개발중입니다", Toast.LENGTH_SHORT).show()
            } else {
                val data = HashMap<String, String>()
                data.put("idx", productId.toString())

                RetrofitManager.instance.postPurchaseProduct(
                    data = data,
                    onCompleteListener = { responseState ->
                        when (responseState) {
                            RESPONSE_STATUS.OKAY -> {
                                binding.llPurchaseView.visibility = View.GONE
                                productList.refreshData()
                            }
                            RESPONSE_STATUS.FAIL -> {

                            }
                        }
                    })
            }
        }

        supportFragmentManager.commit {
            productList = ProductListFragment()
            productList.setPurchasedView(binding.llPurchaseView)
            val map = MapFragment()
            map.setPurchasedView(binding.llPurchaseView)
            val search = SearchFragment()
            val info = InfoFragment()

            add(R.id.fl_fragment, productList, "ProductList")
            hide(productList)
            add(R.id.fl_fragment, map, "Map")
            ll_btn_map.isSelected = true
            add(R.id.fl_fragment, search, "Search")
            hide(search)
            add(R.id.fl_fragment, info, "Info")
            hide(info)
        }

        ll_btn_store.setOnClickListener {
            onClickNaviBottomItem(NAVI_BOTTOM_TYPE.PRODUCT_LIST)
        }
        ll_btn_map.setOnClickListener {
            onClickNaviBottomItem(NAVI_BOTTOM_TYPE.MAP)
        }
        ll_btn_search.setOnClickListener {
            onClickNaviBottomItem(NAVI_BOTTOM_TYPE.SEARCH)
        }
        ll_btn_favorit.setOnClickListener {
            Toast.makeText(this, "We are preparing", Toast.LENGTH_SHORT).show()
        }
        ll_btn_info.setOnClickListener {
//            onClickNaviBottomItem(NAVI_BOTTOM_TYPE.INFO)
            val intent = Intent(this, SignInActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun onClickNaviBottomItem(type: NAVI_BOTTOM_TYPE) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        val productList = manager.findFragmentByTag("ProductList")
        val map = manager.findFragmentByTag("Map")
        val search = manager.findFragmentByTag("Search")
        val info = manager.findFragmentByTag("Info")

        if (productList != null) ft.hide(productList)
        if (map != null) ft.hide(map)
        if (search != null) ft.hide(search)
        if (info != null) ft.hide(info)

        ll_btn_store.isSelected = false
        ll_btn_map.isSelected = false
        ll_btn_search.isSelected = false
        ll_btn_info.isSelected = false

        when (type) {
            NAVI_BOTTOM_TYPE.PRODUCT_LIST -> {
                if (productList != null) ft.show(productList)
                ll_btn_store.isSelected = true
            }

            NAVI_BOTTOM_TYPE.MAP -> {
                if (map != null) ft.show(map)
                ll_btn_map.isSelected = true
            }

            NAVI_BOTTOM_TYPE.SEARCH -> {
                if (search != null) ft.show(search)
                ll_btn_search.isSelected = true
            }

            NAVI_BOTTOM_TYPE.INFO -> {
                if (info != null) ft.show(info)
                ll_btn_info.isSelected = true
            }
        }
        ft.commitAllowingStateLoss()
    }
}