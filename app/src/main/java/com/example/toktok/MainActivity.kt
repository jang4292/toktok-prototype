package com.example.toktok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.toktok.databinding.ActivityMainBinding
import com.example.toktok.ui.info.InfoFragment
import com.example.toktok.ui.list.StoreListFragment
import com.example.toktok.ui.map.MapFragment
import com.example.toktok.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide();

        supportFragmentManager.commit {
            val store = StoreListFragment();
            val map = MapFragment();
            val search = SearchFragment();
            val info = InfoFragment();
            add(R.id.fl_fragment, store, "Store")
            ll_btn_store.isSelected = true;
            add(R.id.fl_fragment, map, "Map")
            hide(map)
            add(R.id.fl_fragment, search, "Search")
            hide(search)
            add(R.id.fl_fragment, info, "Info")
            hide(info)
        }

        ll_btn_store.setOnClickListener {
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

            ll_btn_store.isSelected = false;
            ll_btn_map.isSelected = false;
            ll_btn_search.isSelected = false;
            ll_btn_info.isSelected = false;

            if (store != null) {
                ll_btn_store.isSelected = true;
                ft.show(store)
            }

            ft.commitAllowingStateLoss()
        }

        ll_btn_map.setOnClickListener {
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

            ll_btn_store.isSelected = false;
            ll_btn_map.isSelected = false;
            ll_btn_search.isSelected = false;
            ll_btn_info.isSelected = false;

            if (map != null) {
                ll_btn_map.isSelected = true;
                ft.show(map)
            }

            ft.commitAllowingStateLoss()
        }

        ll_btn_search.setOnClickListener {
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

            ll_btn_store.isSelected = false;
            ll_btn_map.isSelected = false;
            ll_btn_search.isSelected = false;
            ll_btn_info.isSelected = false;

            if (search != null) {
                ll_btn_search.isSelected = true;
                ft.show(search)
            }

            ft.commitAllowingStateLoss()
        }

        ll_btn_info.setOnClickListener {
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

            ll_btn_store.isSelected = false;
            ll_btn_map.isSelected = false;
            ll_btn_search.isSelected = false;
            ll_btn_info.isSelected = false;

            if (info != null) {
                ll_btn_info.isSelected = true;
                ft.show(info)
            }

            ft.commitAllowingStateLoss()
        }
    }
}