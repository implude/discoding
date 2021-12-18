package com.example.discoding

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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

class EditBot:AppCompatActivity() {

    private val ebOPEN_GALLERY = 1

    var gson = GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://selfstudy.kro.kr:5000/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val service = retrofit.create(request_from_editbot::class.java)
    private val service1 = retrofit.create(remove_bot::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_bot)



        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")

        val ebBotName_text = findViewById<EditText>(R.id.ebBotName_text)
        val ebBack_button = findViewById<ImageButton>(R.id.ebBack_button) //에딧봇에 있는 뒤로가기 버튼
        val ebShare_btn = findViewById<ImageView>(R.id.ebShare_btn) //에딧봇에 있는 공유로 가기 버튼
        val ebCoding_btn = findViewById<ImageView>(R.id.ebCoding_btn) //에딧봇에있는 코딩하러가기 버튼
        val ebEdit_btn = findViewById<ImageView>(R.id.ebEdit_btn)
        val ebDes_explain = findViewById<EditText>(R.id.ebDes_explain)
        val ebDelete_btn= findViewById<ImageView>(R.id.ebDelete_btn)
        val ebtoken = findViewById<EditText>(R.id.ebtoken)


        ebBotName_text.hint = description.toString()

        ebBotName_text.hint = name.toString()

        ebBack_button.setOnClickListener() { //에딧봇에서 뒤로가기버튼누르면 메인으로 가기
            val ebGo_main = Intent(this, Appmain::class.java)
            startActivity(ebGo_main)
        }
        ebShare_btn.setOnClickListener() { //에딧봇에서 공유버튼 누르면 공유창 가기
//            val ebGo_share = Intent(this, Share::class.java)
//            startActivity(ebGo_share)
            try {
                val sendText = "제 디스코드 봇을 소개합니다! %s, %s".format(name.toString(),description.toString()) +
                        "--Discoding에서 제작되었습니다--"
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, sendText)
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, "Share"))
            } catch (ignored: ActivityNotFoundException) {
                Log.d("test", "ignored : $ignored")
            }
        }
        ebDelete_btn.setOnClickListener(){
            service1.delete(name.toString()).enqueue(object : Callback<get_from_back> {
                override fun onResponse(
                    call: Call<get_from_back>,
                    response: Response<get_from_back>
                ) {
                    Log.d("메세지입니다", response.body()?.msg.toString())
                }

                override fun onFailure(call: Call<get_from_back>, t: Throwable) {
                    Log.d("result", t.toString())
                }
            })
            val ebGo_main = Intent(this, Appmain::class.java)
            startActivity(ebGo_main)
        }

        ebCoding_btn.setOnClickListener(){
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://selfstudy.kro.kr:5000/block-coding"))

            startActivity(intent)
        }

        ebEdit_btn.setOnClickListener(){
            service.noget(name.toString(),ebDes_explain.text.toString(), ebBotName_text.text.toString(), ebtoken.text.toString()).enqueue(object : Callback<post_bot_data> {
                override fun onResponse(
                    call: Call<post_bot_data>,
                    response: Response<post_bot_data>
                ) {
                    Log.d("메세지입니다", response.body()?.msg.toString())
                }

                override fun onFailure(call: Call<post_bot_data>, t: Throwable) {
                    Log.d("result", t.toString())
                }
            })

            val intent = Intent(this, Appmain::class.java)
            startActivity(intent)
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