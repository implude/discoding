package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CreateBot :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_bot)

        val cbBack_button = findViewById<ImageButton>(R.id.cbBack_button) //뒤로가기 버튼
        val cbCreatBot_btn = findViewById<android.widget.Button>(R.id.cbCreatBot_btn)// 봇 만들기 버튼
        val cbGo_main = Intent(this, Appmain::class.java) // 뒤로가기버튼이나 봇만들기 버튼 둘 중 아무거나 눌러도 메인으로 감

        cbBack_button.setOnClickListener() { //뒤로가기 버튼 클릭
            startActivity(cbGo_main)
        }
        cbCreatBot_btn.setOnClickListener() { //봇만들기 버튼 클릭
            val cbGo = Intent(this, Appmain::class.java)
            startActivity(cbGo_main)
        }
    }
}