package com.example.discoding

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class EditBot:AppCompatActivity() {

    private val ebOPEN_GALLERY = 1

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
    private fun ebopenGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, ebOPEN_GALLERY)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val ebProfile_image = findViewById<ImageView>(R.id.ebImage_View)
        if(resultCode == Activity.RESULT_OK){
            var ImmageData : Uri? = data?.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImmageData)
                ebProfile_image.setImageBitmap(bitmap)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}