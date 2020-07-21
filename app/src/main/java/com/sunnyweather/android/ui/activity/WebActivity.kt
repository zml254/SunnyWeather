package com.sunnyweather.android.ui.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sunnyweather.android.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        setSupportActionBar(tb_web_bar)
        iv_web_return.setOnClickListener { finish() }
        val intent = intent
        val url = intent.getStringExtra("webUrl")
        val webView = findViewById<View>(R.id.wv_web_web) as WebView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }
}