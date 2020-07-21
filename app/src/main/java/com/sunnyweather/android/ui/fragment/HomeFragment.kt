package com.sunnyweather.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyweather.android.R
import com.sunnyweather.android.bean.Article
import com.sunnyweather.android.bean.Banner
import com.sunnyweather.android.ui.adapter.HomeAdapter
import com.sunnyweather.android.viewmodel.MoreViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*

class HomeFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MoreViewModel::class.java) }
    private lateinit var articles: List<Article>
    private lateinit var banners: List<Banner>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                articles = viewModel.getArticles().data.datas
                banners = viewModel.getBanner().data
                rv_home_content.adapter =
                    HomeAdapter(articles, rv_home_content, banners)
                rv_home_content.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}