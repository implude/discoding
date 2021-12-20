package com.example.discoding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class coding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coding)
        val webview = findViewById<WebView>(R.id.webview)
        val frameVideo = "<html><style>body {margin: 0}</style><body><iframe width=\"1080\" " +
                "height=\"2340\" src=\"http://selfstudy.kro.kr:5000/block-coding\" frameborder=\"0\" " +
                "allowfullscreen></iframe></body></html>"
        webview.apply        {
            this.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                    return if (url != null && url.startsWith("https://")) {
                        view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        true
                    } else {
                        false
                    }
                }
            }
            this.webChromeClient = WebChromeClient()
            val webSettings = this.settings
            webSettings.javaScriptEnabled = true
            this.loadData(frameVideo, "text/html", "utf-8")
            this.minimumWidth = width.toInt()
            this.minimumHeight = height.toInt()
            this.loadUrl(frameVideo)
        }
    }

}