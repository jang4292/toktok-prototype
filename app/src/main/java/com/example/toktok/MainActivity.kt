package com.example.toktok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.toktok.databinding.ActivityMainBinding
import com.example.toktok.ui.info.InfoFragment
import com.example.toktok.ui.list.ProductListFragment
import com.example.toktok.ui.map.MapFragment
import com.example.toktok.ui.search.SearchFragment
import com.example.toktok.utils.NAVI_BOTTOM_TYPE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            val productList = ProductListFragment()
            val map = MapFragment()
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
        ll_btn_info.setOnClickListener {
            onClickNaviBottomItem(NAVI_BOTTOM_TYPE.INFO)
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