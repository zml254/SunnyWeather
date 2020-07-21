package com.sunnyweather.android.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.activity.WebActivity
import com.sunnyweather.android.bean.Banner

class HomeBannerAdapter(private val banners: List<Banner>, val parent: ViewGroup) :
    RecyclerView.Adapter<HomeBannerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_home_banner_item_image)
        var url: String? = null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_home_banner, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (banners.isEmpty()) {
            return
        }
        Glide.with(parent.context)
            .load(banners[position % banners.size].imagePath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)
        holder.url = banners[position % banners.size].url
        holder.imageView.setOnClickListener {
            val intent = Intent(parent.context, WebActivity::class.java)
            intent.putExtra("webUrl", holder.url)
            parent.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

}