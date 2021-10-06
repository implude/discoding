package com.example.discoding

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class CreateBot :AppCompatActivity(){
    private val OPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_bot)

        val cbBack_button = findViewById<ImageButton>(R.id.cbBack_button) //뒤로가기 버튼
        val cbCreatBot_btn = findViewById<android.widget.Button>(R.id.cbCreatBot_btn)// 봇 만들기 버튼
        val cbProfile_image = findViewById<ImageView>(R.id.cbProfile_image)
        val cbGo_main = Intent(this, Appmain::class.java) // 뒤로가기버튼이나 봇만들기 버튼 둘 중 아무거나 눌러도 메인으로 감


        cbBack_button.setOnClickListener() { //뒤로가기 버튼 클릭
            startActivity(cbGo_main)
        }
        cbCreatBot_btn.setOnClickListener() { //봇만들기 버튼 클릭
            startActivity(cbGo_main)
        }
        cbProfile_image.setOnClickListener() { //갤러리 올리기 중
            openGallery()
        }
    }

    //프로필 이미지 선택
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    @Override //(https://believecom.tistory.com/722)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val cbProfile_image = findViewById<ImageView>(R.id.cbProfile_image)
        if(resultCode == Activity.RESULT_OK){
            var ImmageData : Uri? = data?.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImmageData)
                cbProfile_image.setImageBitmap(bitmap)
            }
            catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }
}

