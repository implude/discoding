package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EditBot:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_bot)

        val ebBack_button = findViewById<ImageButton>(R.id.ebBack_button) //에딧봇에 있는 뒤로가기 버튼
        val ebShare_btn = findViewById<ImageView>(R.id.ebShare_btn) //에딧봇에 있는 공유로 가기 버튼
        val ebCoding_btn = findViewById<ImageView>(R.id.ebCoding_btn) //에딧봇에있는 코딩하러가기 버튼

        ebBack_button.setOnClickListener() { //에딧봇에서 뒤로가기버튼누르면 메인으로 가기
            val ebGo_main = Intent(this, Appmain::class.java)
            startActivity(ebGo_main)
        }
        ebShare_btn.setOnClickListener() { //에딧봇에서 공유버튼 누르면 공유창 가기
            val ebGo_share = Intent(this, Share::class.java)
            startActivity(ebGo_share)
        }

    }
}