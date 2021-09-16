package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Events:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events)

        val eBack_button = findViewById<ImageButton>(R.id.eBack_button) //이벤트창에 있는 뒤로가기 버튼

        eBack_button.setOnClickListener() { //이벤트 창에서 메인으로 넘어가기
            val eGo_main = Intent(this, Appmain::class.java)
            startActivity(eGo_main)
        }
    }
}