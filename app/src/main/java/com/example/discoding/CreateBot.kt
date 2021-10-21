package com.example.discoding

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class CreateBot :AppCompatActivity(){
    //서버통신
    var gson= GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://34.64.200.191:4000/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val service = retrofit.create(cbrequest ::class.java)

    //오픈갤러리
    private val cbOPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_bot)

        val cbBack_button = findViewById<ImageButton>(R.id.cbBack_button) //뒤로가기 버튼
        val cbCreatBot_btn = findViewById<android.widget.Button>(R.id.cbCreatBot_btn)// 봇 만들기 버튼
        val cbProfile_image = findViewById<ImageView>(R.id.cbProfile_image)
        val cbGo_main = Intent(this, Appmain::class.java) // 뒤로가기버튼이나 봇만들기 버튼 둘 중 아무거나 눌러도 메인으로 감
        val cbInputBotName = findViewById<EditText>(R.id.cbExplain_botname) //봇이름 입력
        val cbInputBotDescription = findViewById<EditText>(R.id.cbDescription_explain)


        cbBack_button.setOnClickListener() { //뒤로가기 버튼 클릭
            startActivity(cbGo_main)
        }

        cbCreatBot_btn.setOnClickListener() { //봇만들기 버튼 클릭
            val cbSetBotName = cbInputBotName.getText().toString() //봇네임 입력된 것을 받음
            val cbSetBotDescription = cbInputBotDescription.getText().toString() //봇설명 입력된것을 받음
            //서버통신 봇네임 받아서 보내야함
            service.getbotinfo("김성훈", cbSetBotName,cbSetBotDescription,"","").enqueue(object : Callback<cbresult> {
                override fun onResponse(
                    call: Call<cbresult>,
                    response: Response<cbresult>
                ) {
                    Log.d("body",response.body()?.msg.toString())
                }

                override fun onFailure(call: Call<cbresult>, t: Throwable) {
                    Log.d("result",t.toString())
                }
            })
            startActivity(cbGo_main)
        }

        cbProfile_image.setOnClickListener() { //갤러리 올리기 중
            cbopenGallery()
        }




    }

    //프로필 이미지 선택
    private fun cbopenGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, cbOPEN_GALLERY)
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

