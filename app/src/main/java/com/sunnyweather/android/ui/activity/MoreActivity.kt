package com.sunnyweather.android.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.adapter.FragmentAdapter
import com.sunnyweather.android.viewmodel.MoreViewModel
import kotlinx.android.synthetic.main.activity_more.*

class MoreActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MoreViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        val fragmentAdapter = FragmentAdapter(this, viewModel.fragments)
        vp2_more_content.adapter = fragmentAdapter
        TabLayoutMediator(tl_more_tab, vp2_more_content,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        viewModel.initTabItem(tab)
                        viewModel.updateTabItem(tab, position, true)
                    }
                    else -> {
                        viewModel.initTabItem(tab)
                        viewModel.updateTabItem(tab, position, false)
                    }
                }
            }).attach()


        tl_more_tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewModel.updateTabItem(tab, false)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewModel.updateTabItem(tab, true)
                }
            }
        })
    }



}