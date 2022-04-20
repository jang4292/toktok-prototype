package com.example.toktok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.toktok.data.Store
import com.example.toktok.databinding.ActivityMainBinding
import com.example.toktok.ui.info.InfoFragment
import com.example.toktok.ui.list.StoreDetailFragment
import com.example.toktok.ui.list.StoreListFragment
import com.example.toktok.ui.map.MapFragment
import com.example.toktok.ui.search.SearchFragment
import com.example.toktok.utils.NAVI_BOTTOM_TYPE
import com.example.toktok.utils.VIEW_STATE
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentPosition: VIEW_STATE = VIEW_STATE.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide();

        supportFragmentManager.commit {
            val store = StoreListFragment()
            val map = MapFragment()
            val search = SearchFragment()
            val info = InfoFragment()

            add(R.id.fl_fragment, store, "Store")
            ll_btn_store.isSelected = true
            add(R.id.fl_fragment, map, "Map")
            hide(map)
            add(R.id.fl_fragment, search, "Search")
            hide(search)
            add(R.id.fl_fragment, info, "Info")
            hide(info)
        }

        ll_btn_store.setOnClickListener {
            onClickNaviBottomItem(NAVI_BOTTOM_TYPE.STORE)
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

        val store = manager.findFragmentByTag("Store")
        val map = manager.findFragmentByTag("Map")
        val search = manager.findFragmentByTag("Search")
        val info = manager.findFragmentByTag("Info")
        val detail = manager.findFragmentByTag("Detail")

        if (store != null) ft.hide(store)
        if (map != null) ft.hide(map)
        if (search != null) ft.hide(search)
        if (info != null) ft.hide(info)
        if (detail != null) ft.remove(detail)

        currentPosition = VIEW_STATE.HOME
        ll_btn_store.isSelected = false
        ll_btn_map.isSelected = false
        ll_btn_search.isSelected = false
        ll_btn_info.isSelected = false

        when (type) {
            NAVI_BOTTOM_TYPE.STORE -> {
                if (store != null) ft.show(store)
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

    fun showStoreDetailFragment(data: Store, imageIndex: Int) {
        currentPosition = VIEW_STATE.DETAIL
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        val store = manager.findFragmentByTag("Store")
        val map = manager.findFragmentByTag("Map")
        val search = manager.findFragmentByTag("Search")
        val info = manager.findFragmentByTag("Info")

        if (store != null) ft.hide(store)
        if (map != null) ft.hide(map)
        if (search != null) ft.hide(search)
        if (info != null) ft.hide(info)

        val bundle = Bundle()
        bundle.putSerializable("data", data as Serializable)
        bundle.putInt("imageIndex", imageIndex as Int)
        val detail = StoreDetailFragment()
        detail.arguments = bundle
        ft.add(R.id.fl_fragment, detail, "Detail")
        ft.commitAllowingStateLoss()
    }

    override fun onBackPressed() {

        if (currentPosition == VIEW_STATE.DETAIL) {
            when {
                ll_btn_store.isSelected -> {
                    onClickNaviBottomItem(NAVI_BOTTOM_TYPE.STORE)
                }
                ll_btn_map.isSelected -> {
                    onClickNaviBottomItem(NAVI_BOTTOM_TYPE.MAP)
                }
                ll_btn_search.isSelected -> {
                    onClickNaviBottomItem(NAVI_BOTTOM_TYPE.SEARCH)
                }
                ll_btn_info.isSelected -> {
                    onClickNaviBottomItem(NAVI_BOTTOM_TYPE.INFO)
                }
                else -> {
                    super.onBackPressed()
                }
            }
        } else {
            super.onBackPressed()
        }
    }
}