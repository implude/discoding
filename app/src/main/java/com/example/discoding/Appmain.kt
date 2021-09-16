package com.example.discoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Appmain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val mHosting_btn = findViewById<android.widget.Button>(R.id.mHosting_btn) //메인페이지에 있는 호스팅 버튼
        val mShare_btn = findViewById<android.widget.Button>(R.id.mShare_btn) //메인페이지에 있는 공유 버튼
        val mPlus_btn = findViewById<android.widget.Button>(R.id.mPlus_btn) //메인페이지에 있는 플러스 버튼

        mHosting_btn.setOnClickListener() { //메인에서 호스팅 페이지로
            val mGo_hosting = Intent(this, HostingActivity::class.java)
            startActivity(mGo_hosting)
        }
        mShare_btn.setOnClickListener() { //메인에서 공유 페이지로
            val mGo_share = Intent(this, Share::class.java)
            startActivity(mGo_share)
        }
        mPlus_btn.setOnClickListener() { //메인에서 봇생성페이지로
            val mGo_CreateBot = Intent(this, CreateBot::class.java)
            startActivity(mGo_CreateBot)
        }
    }
}