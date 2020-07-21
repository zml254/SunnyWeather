package com.sunnyweather.android.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.ui.activity.ExtraActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.bean.WxArticleResponse

class WechatAdapter(private val wxs: List<WxArticleResponse.Wx>) : RecyclerView.Adapter<WechatAdapter.ViewHolder>() {

    private lateinit var parent: ViewGroup

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView  = itemView.findViewById(R.id.tv_fragment_wechat_name)
        val imageView:ImageView = itemView.findViewById(R.id.iv_wechat_return)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.parent = parent
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_wechat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wxs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = wxs[position].name
        holder.name.setOnClickListener {
            val intent = Intent(parent.context, ExtraActivity::class.java).apply {
                putExtra("WxID", wxs[position].id)
            }
            parent.context.startActivity(intent)
        }
        holder.imageView.setOnClickListener {
            val intent = Intent(parent.context, ExtraActivity::class.java).apply {
                putExtra("WxID", wxs[position].id)
            }
            parent.context.startActivity(intent)
        }
    }

}