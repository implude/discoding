package com.example.discoding

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Share:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share)


        val sMain_btn = findViewById<android.widget.Button>(R.id.sMain_btn)
        val sHosting_btn = findViewById<android.widget.Button>(R.id.sHosting_btn)

        sMain_btn.setOnClickListener() { //공유창에서 메인창으로
            val sGo_main = Intent(this, Appmain::class.java)
            startActivity(sGo_main)
        }
        sHosting_btn.setOnClickListener() { //공유창에서 호스팅페이지로
            val sGo_hosting = Intent(this, HostingActivity::class.java)
            startActivity(sGo_hosting)

        }
        val webView = findViewById<WebView>(R.id.webview)
        webView.settings.javaScriptEnabled = true // 자바 스크립트 허용

        // 웹뷰안에 새 창이 뜨지 않도록 방지
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        // 원하는 주소를 WebView에 연결
        webView.loadUrl("https://discordbotlist.com/")

    }
}

