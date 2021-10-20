package com.example.discoding

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HostingService {
    @FormUrlEncoded
    @POST("/bot/hosting_page")
    fun hotingloading(
        @Field("userid") userid: String
    ): Call<hotingpagevalue>
}

data class hotingpagevalue(
    val userid: String,
    val remaintime: String
)
