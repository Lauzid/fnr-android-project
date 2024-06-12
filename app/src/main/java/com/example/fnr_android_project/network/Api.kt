package com.example.fnr_android_project.network

import com.example.fnr_android_project.model.BaseSetResponse
import com.example.fnr_android_project.model.Card
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/sets")
    fun getSets(): Call<BaseSetResponse>

    @GET("/cards/search")
    fun getCards(@Query("q") query: String): Call<BaseSetResponse>

    @GET("cards/{id}")
    fun getCardDetails(@Path("id") cardId: String?): Call<Card?>?

}