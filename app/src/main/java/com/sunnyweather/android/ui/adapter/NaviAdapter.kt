package com.sunnyweather.android.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.activity.WebActivity
import com.sunnyweather.android.bean.NaviResponse

class NaviAdapter(private val naviResponse: NaviResponse) : RecyclerView.Adapter<NaviAdapter.ViewHolder>() {

    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.parent = parent
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_navi, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = naviResponse.data[position].name
        var j = 0
        while (j < naviResponse.data[position].articles.size) {
            val chip = Chip(parent.context)
            chip.textSize = 14f
            chip.setTextColor(Color.BLACK)
            chip.text = naviResponse.data[position].articles[j].title
            chip.setOnClickListener {
                val intent = Intent(parent.context, WebActivity::class.java)
                intent.putExtra("webUrl", naviResponse.data[position].articles[j].link)
                parent.context.startActivity(intent)
            }
            holder.group.addView(chip)
            j++
        }
    }

    override fun getItemCount(): Int {

        return naviResponse.data.size

    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.fragment_navigation_item_title)
        val group: ChipGroup = itemView.findViewById(R.id.rv_item_cp_navigation)

    }

}