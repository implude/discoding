package com.example.discoding


import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

data class get_info(
    val bot_name: String,
    val des : String,
    val img : String
)

interface Request3 {

    @FormUrlEncoded
    @POST("/login")
    fun send_uuid(
        @Field("UUID") UUID: String
    ): Call<get_info>

}