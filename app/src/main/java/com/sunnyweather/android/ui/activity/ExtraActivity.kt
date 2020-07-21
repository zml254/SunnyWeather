package com.sunnyweather.android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.adapter.ExtraAdapter
import com.sunnyweather.android.viewmodel.MoreViewModel
import kotlinx.android.synthetic.main.activity_extra.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExtraActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MoreViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra)
        val id = intent.getIntExtra("ArticleID", 0)
        iv_extra_return.setOnClickListener {
            finish()
        }
        if (id != 0) {
            GlobalScope.launch {
                withContext(Dispatchers.Main){
                    rv_extra_content.adapter = ExtraAdapter(viewModel.getArticles(id).data.datas)
                    rv_extra_content.layoutManager = LinearLayoutManager(this@ExtraActivity)
                }
            }
        }
        val wxId = intent.getIntExtra("WxID", 0)
        if (wxId != 0) {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    rv_extra_content.adapter =
                        ExtraAdapter(viewModel.getArticleByWx(wxId).data.datas)
                    rv_extra_content.layoutManager = LinearLayoutManager(this@ExtraActivity)
                }
            }
        }
    }
}