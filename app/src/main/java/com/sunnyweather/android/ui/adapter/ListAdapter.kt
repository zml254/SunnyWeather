package com.sunnyweather.android.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.ui.activity.ExtraActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.bean.TreeResponse

class ListAdapter(private val tree: TreeResponse) :RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var parent: ViewGroup? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_list,
            parent, false
        )
        val viewHolder = ViewHolder(view)
        viewHolder.build(tree.data[viewType].children.size)
        this.parent = parent
        return viewHolder
    }

    override fun getItemCount(): Int {
        return tree.data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textViews = arrayOfNulls<TextView>(14)
        val groupName: TextView? = view.findViewById(R.id.tv_list_item_title)

        fun build(length: Int) {
            if (length == 0) return
            textViews[0] = view.findViewById(R.id.fragment_system_item_content1)
            textViews[0]?.visibility = View.VISIBLE
            if (length == 1) return
            textViews[1] = view.findViewById(R.id.fragment_system_item_content2)
            textViews[1]?.visibility = View.VISIBLE
            if (length == 2) return
            textViews[2] = view.findViewById(R.id.fragment_system_item_content3)
            textViews[2]?.visibility = View.VISIBLE
            if (length == 3) return
            textViews[3] = view.findViewById(R.id.fragment_system_item_content4)
            textViews[3]?.visibility = View.VISIBLE
            if (length == 4) return
            textViews[4] = view.findViewById(R.id.fragment_system_item_content5)
            textViews[4]?.visibility = View.VISIBLE
            if (length == 5) return
            textViews[5] = view.findViewById(R.id.fragment_system_item_content6)
            textViews[5]?.visibility = View.VISIBLE
            if (length == 6) return
            textViews[6] = view.findViewById(R.id.fragment_system_item_content7)
            textViews[6]?.visibility = View.VISIBLE
            if (length == 7) return
            textViews[7] = view.findViewById(R.id.fragment_system_item_content8)
            textViews[7]?.visibility = View.VISIBLE
            if (length == 8) return
            textViews[8] = view.findViewById(R.id.fragment_system_item_content9)
            textViews[8]?.visibility = View.VISIBLE
            if (length == 9) return
            textViews[9] = view.findViewById(R.id.fragment_system_item_content10)
            textViews[9]?.visibility = View.VISIBLE
            if (length == 10) return
            textViews[10] = view.findViewById(R.id.fragment_system_item_content11)
            textViews[10]?.visibility = View.VISIBLE
            if (length == 11) return
            textViews[11] = view.findViewById(R.id.fragment_system_item_content12)
            textViews[11]?.visibility = View.VISIBLE
            if (length == 12) return
            textViews[12] = view.findViewById(R.id.fragment_system_item_content13)
            textViews[12]?.visibility = View.VISIBLE
            if (length == 13) return
            textViews[13] = view.findViewById(R.id.fragment_system_item_content14)
            textViews[13]?.visibility = View.VISIBLE
            if (length == 14) return
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val treeChildren = tree.data[position]

        val length = treeChildren.children.size
        var i = 0
        holder.groupName?.text = treeChildren.name
        while (i < 14 && i < length) {
            val j = i
            holder.textViews[i]?.text = treeChildren.children[i].name
            holder.textViews[i]?.setOnClickListener(View.OnClickListener {
                val intent = Intent(parent?.context, ExtraActivity::class.java).apply {
                    putExtra("ArticleID", treeChildren.children[j].id)
                }
                parent?.context?.startActivity(intent)
            })
            i++
        }
    }
}