package com.example.discoding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
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

            this.settings.apply {
                useWideViewPort = true
                setSupportZoom(true)
                builtInZoomControls = true
                domStorageEnabled = true
                loadsImagesAutomatically = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                javaScriptEnabled = true
            }
            this.webChromeClient = WebChromeClient()
            this.loadUrl("http://selfstudy.kro.kr:5000/block-coding")
        }
    }

}