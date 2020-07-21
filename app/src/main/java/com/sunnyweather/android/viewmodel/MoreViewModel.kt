package com.sunnyweather.android.viewmodel

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.tabs.TabLayout
import com.sunnyweather.android.R
import com.sunnyweather.android.repository.Repository
import com.sunnyweather.android.ui.fragment.HomeFragment
import com.sunnyweather.android.ui.fragment.ListFragment
import com.sunnyweather.android.ui.fragment.NaviFragment
import com.sunnyweather.android.ui.fragment.WechatFragment

class MoreViewModel : ViewModel() {

    var fragments: List<Fragment> =
        listOf(
            HomeFragment(),
            ListFragment(),
            WechatFragment(),
            NaviFragment()
        )

    suspend fun getNavi() = Repository.getNavi()

    suspend fun getArticleByWx(id: Int) = Repository.getArticleByWx(id)

    suspend fun getWxArticle() = Repository.getWxArticle()

    suspend fun getArticles(id: Int) = Repository.getArticle(id)

    suspend fun getArticles() = Repository.getArticle()

    suspend fun getBanner() = Repository.getBanner()

    suspend fun getTree() = Repository.getTree()

    fun initTabItem(tab: TabLayout.Tab) {
        tab.setCustomView(R.layout.custom_indicator)
    }

    fun updateTabItem(tab: TabLayout.Tab, select: Boolean) {
        updateTabItem(tab, tab.position, select)
    }

    fun updateTabItem(tab: TabLayout.Tab, position: Int, select: Boolean) {

        val view = tab.customView?.findViewById<TextView>(R.id.item_vp2_tv)
        val image = tab.customView?.findViewById<ImageView>(R.id.item_vp2_icon)
        if (select) {
            image?.setImageResource(getImageByPosition(position))
            view?.text = getTextByPosition(position)
            view?.setTextColor(Color.parseColor("#29b0ff"))
        } else {
            image?.setImageResource(getImageByPosition(position + 4))
            view?.text = getTextByPosition(position)
            view?.setTextColor(Color.parseColor("#cdcdcd"))
        }

    }

    private fun getImageByPosition(position: Int):Int {
        return when (position) {
            0 -> R.drawable.ic_homes_selected
            1 -> R.drawable.ic_list_selected
            2 -> R.drawable.ic_wechat_selected
            3 -> R.drawable.ic_navigation_selected
            4 -> R.drawable.ic_home_unselected
            5 -> R.drawable.ic_list_unselected
            6 -> R.drawable.ic_wechat_unselected
            7 -> R.drawable.ic_navigation_unselected
            else -> R.drawable.ic_launcher_foreground
        }
    }

    private fun getTextByPosition(position: Int): String {
        return when (position) {
            0 -> "首页"
            1 -> "知识体系"
            2 -> "公众号"
            else -> "导航"
        }
    }

}