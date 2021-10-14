package com.example.discoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Appmain : AppCompatActivity() {

    var gson= GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ecce-121-66-18-107.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(UserRequest ::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        service.getuserinfo("online","","","").enqueue(object : Callback<MemberResult> {
            override fun onResponse(
                call: Call<MemberResult>,
                response: Response<MemberResult>
            ) {
                Log.d("body",response.body()?.val1.toString())
            }

            override fun onFailure(call: Call<MemberResult>, t: Throwable) {
                Log.d("result",t.toString())
            }
        })


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