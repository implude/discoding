package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CreateBot :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_bot)

        val cbBack_button = findViewById<ImageButton>(R.id.cbBack_button) //뒤로가기 버튼
        val cbCreatBot_btn = findViewById<android.widget.Button>(R.id.cbCreatBot_btn)// 봇 만들기 버튼
        val cbProfile_image = findViewById<android.widget.Button>(R.id.cbProfile_image)
        val cbGo_main = Intent(this, Appmain::class.java) // 뒤로가기버튼이나 봇만들기 버튼 둘 중 아무거나 눌러도 메인으로 감
        val FLAG_REQ_STORAGE = 0

        cbBack_button.setOnClickListener() { //뒤로가기 버튼 클릭
            startActivity(cbGo_main)
        }
        cbCreatBot_btn.setOnClickListener() { //봇만들기 버튼 클릭
            startActivity(cbGo_main)
        }
        cbProfile_image.setOnClickListener() { //갤러리 올리기 중
            val getProfile_image = Intent(Intent.ACTION_PICK)
            getProfile_image.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(getProfile_image, FLAG_REQ_STORAGE)
        }
    }
}
