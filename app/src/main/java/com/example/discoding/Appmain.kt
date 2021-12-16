package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Appmain : AppCompatActivity() {

    var gson= GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:80")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(UserRequest ::class.java)
    private val service2 = retrofit.create(Request2 ::class.java)
    private val service3 = retrofit.create(Request3 ::class.java)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val sharedPreference = getSharedPreferences("UUID", 0)
        val editor = sharedPreference.edit()

        val mainRecycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.mainrecycler) //봇 목록 리사이클러뷰
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
        if(sharedPreference.getString("UUID", null).toString() == "null"){
            service2.getuuid().enqueue(object : Callback<uuid> {
                override fun onResponse(
                    call: Call<uuid>,
                    response: Response<uuid>
                ) {
                    editor.putString("UUID", response.body()?.uuid.toString())
                    editor.apply()
                    service.getuserinfo(response.body()?.uuid.toString()).enqueue(object : Callback<MemberResult> {
                        override fun onResponse(
                            call: Call<MemberResult>,
                            response: Response<MemberResult>
                        ) {
                            Log.d("hfd", response.body()?.msg.toString())
                        }
                        override fun onFailure(call: Call<MemberResult>, t: Throwable) {
                            Log.d("result",t.toString())
                        }
                    })
                }
                override fun onFailure(call: Call<uuid>, t: Throwable) {
                    Log.d("result",t.toString())
                }
            })
        }
        else{
            service3.send_uuid(sharedPreference.getString("UUID", null).toString()).enqueue(object : Callback<get_info> {
                override fun onResponse(
                    call: Call<get_info>,
                    response: Response<get_info>
                ) {
                    //리사이클러뷰 하면됌

                    val a = response.body()?.bot_name.toString()
                    val b = response.body()?.des.toString()
                    val c = response.body()?.img.toString()

                    val a_arr = a.split(" ")
                    val b_arr = b.split("/")
                    val c_arr = c.split("|")
                    val profileList = ArrayList<Profiles>()
                    for(i in 1 until (a_arr.lastIndex + 1)){
                        profileList.add(Profiles(a_arr[i],b_arr[i], c_arr[i]))
                    }
                    mainRecycler.layoutManager = LinearLayoutManager(this@Appmain, LinearLayoutManager.VERTICAL, false)
                    mainRecycler.setHasFixedSize(true)
                    mainRecycler.adapter = ProfileAdapter(profileList)
                }
                override fun onFailure(call: Call<get_info>, t: Throwable) {
                    Log.d("result",t.toString())
                }
            })
        }

    }
}