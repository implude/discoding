package com.example.discoding


import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

data class MemberResult(
    val val1: String
)

interface UserRequest {

    @FormUrlEncoded
    @POST("/{path}")
    fun getuserinfo(
        @Path("path") path: String,
        @Field("userId") userId: String,
        @Field("botName") botname: String,
        @Field("eventName") eventname: String
    ): Call<MemberResult>

}