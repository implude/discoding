package com.example.discoding

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val cbOPEN_GALLERY = 105


class CreateBot :AppCompatActivity() {
    var path: String= ""
    //서버통신

    var gson = GsonBuilder().setLenient().create()
    var image: Uri? = null
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:80")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val service = retrofit.create(cbrequest::class.java)

    //오픈갤러리

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_bot)

        val cbProfile_image = findViewById<ImageView>(R.id.cbProfile_image)
        val cbBack_button = findViewById<ImageButton>(R.id.cbBack_button) //뒤로가기 버튼
        val cbCreatBot_btn = findViewById<Button>(R.id.cbCreatBot_btn)// 봇 만들기 버튼
        val cbGo_main = Intent(this, Appmain::class.java) // 뒤로가기버튼이나 봇만들기 버튼 둘 중 아무거나 눌러도 메인으로 감
        val cbInputBotName = findViewById<EditText>(R.id.cbExplain_botname) //봇이름 입력

        val cbInputBotDescription = findViewById<EditText>(R.id.cbDescription_explain)


        cbBack_button.setOnClickListener() { //뒤로가기 버튼 클릭
            startActivity(cbGo_main)
        }
        val sharedPreference = getSharedPreferences("UUID", 0) //유저 식별자

        cbCreatBot_btn.setOnClickListener() { //봇만들기 버튼 클릭
            val cbSetBotName = cbInputBotName.getText().toString() //봇네임 입력된 것을 받음
            val cbSetBotDescription = cbInputBotDescription.getText().toString() //봇설명 입력된것을 받음
            //서버통신 봇네임 받아서 보내야함
            service.getbotinfo(
                sharedPreference.getString("UUID", null).toString(),
                cbSetBotName,
                cbSetBotDescription,
                path
            ).enqueue(object : Callback<cbresult> {
                override fun onResponse(
                    call: Call<cbresult>,
                    response: Response<cbresult>
                ) {
                    Log.d("body", response.body()?.msg.toString())
                }

                override fun onFailure(call: Call<cbresult>, t: Throwable) {
                    Log.d("result", t.toString())
                }
            })
            startActivity(cbGo_main)
        }

        cbProfile_image.setOnClickListener() {
            openGallery()//갤러리 올리기 중
        }
    }
    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, cbOPEN_GALLERY)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == cbOPEN_GALLERY) {
                var dataUrl : Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, dataUrl)
                    val cbProfile_image1 = findViewById<ImageView>(R.id.cbProfile_image)
                    cbProfile_image1.setImageBitmap(bitmap)
                    path = dataUrl.toString()
                }catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
}