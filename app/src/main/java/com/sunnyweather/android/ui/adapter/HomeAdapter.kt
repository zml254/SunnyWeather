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
import com.sunnyweather.android.bean.Banner

class HomeAdapter(
    private val articleList: List<Article>,
    private val recyclerView: RecyclerView,
    private val banners: List<Banner>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var parent: ViewGroup? = null

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chapterName: TextView? = itemView.findViewById(R.id.tv_home_item_chapterName)
        var niceDate: TextView? = itemView.findViewById(R.id.tv_home_item_niceDate)
        var author: TextView? = itemView.findViewById(R.id.tv_home_item_author)
        var title: TextView? = itemView.findViewById(R.id.tv_home_item_title)
        var link: String? = null

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        this.parent = parent
        when (viewType) {
            VIEW_TYPE_ONE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_home_banner, recyclerView, false)
                return BannerViewHolder(
                    view,
                    parent,
                    banners
                )
            }
            VIEW_TYPE_TWO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_header_home, recyclerView, false)
                return ViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.rv_item_home, parent, false
                )
                return ViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        if (position == 0 || position == 1) {
            return
        }
        val article: Article = articleList[position - 2]
        holder.title?.text = article.title
        holder.author?.text = article.author
        holder.chapterName?.text = article.chapterName
        holder.niceDate?.text = article.niceDate
        holder.link = article.link
        holder.title?.setOnClickListener {
            val intent = Intent(parent!!.context, WebActivity::class.java)
            intent.putExtra("webUrl", holder.link)
            parent!!.context.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_ONE
        } else if (position == 1) {
            VIEW_TYPE_TWO
        } else {
            VIEW_TYPE_THREE
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    companion object {
        private const val VIEW_TYPE_ONE = 1
        private const val VIEW_TYPE_TWO = 2
        private const val VIEW_TYPE_THREE = 3
    }

}