package com.sunnyweather.android.ui.adapter

import android.os.Handler
import android.os.Message
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.bean.Banner

class BannerViewHolder(itemView: View, parent: ViewGroup, banners: List<Banner>) : HomeAdapter.ViewHolder(itemView) {

    class MyHandle(private val recyclerView: RecyclerView) : Handler() {

        private var currentIndex = 0

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                if (currentIndex == 4) {
                    currentIndex = 0
                    recyclerView.scrollToPosition(100)
                }
                recyclerView.smoothScrollToPosition(++currentIndex + 100)
                recyclerView.adapter!!.notifyDataSetChanged()
                sendEmptyMessageDelayed(1, 3000)
            }
        }
    }

    init {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.banner_home)
        val layoutManager = LinearLayoutManager(
            parent.context,
            RecyclerView.HORIZONTAL, false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = HomeBannerAdapter(banners, parent)
        PagerSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView.scrollToPosition(100)
        val handler =
            MyHandle(
                recyclerView
            )
        handler.sendEmptyMessage(1)
    }

}