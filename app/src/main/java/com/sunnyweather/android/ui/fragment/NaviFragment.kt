package com.sunnyweather.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.adapter.NaviAdapter
import com.sunnyweather.android.viewmodel.MoreViewModel
import kotlinx.android.synthetic.main.fragment_navi.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NaviFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(MoreViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                rv_navigation_content.adapter = NaviAdapter(viewModel.getNavi())
                rv_navigation_content.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}