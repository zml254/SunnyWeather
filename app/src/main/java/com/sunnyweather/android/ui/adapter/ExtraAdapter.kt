package com.sunnyweather.android.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.activity.WebActivity
import com.sunnyweather.android.bean.Article

class ExtraAdapter(private val articleList: List<Article>) : RecyclerView.Adapter<ExtraAdapter.ViewHolder>() {

    private lateinit var parent: ViewGroup

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chapterName: TextView? = itemView.findViewById(R.id.tv_home_item_chapterName)
        var niceDate: TextView? = itemView.findViewById(R.id.tv_home_item_niceDate)
        var author: TextView? = itemView.findViewById(R.id.tv_home_item_author)
        var title: TextView? = itemView.findViewById(R.id.tv_home_item_title)
        var link: String? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.parent = parent
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_home, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = articleList[position]
        holder.title?.text = article.title
        holder.author?.text = article.author
        holder.chapterName?.text = article.chapterName
        holder.niceDate?.text = article.niceDate
        holder.link = article.link
        holder.title?.setOnClickListener {
            val intent = Intent(parent.context, WebActivity::class.java)
            intent.putExtra("webUrl", holder.link)
            parent.context.startActivity(intent)
        }
    }
}

