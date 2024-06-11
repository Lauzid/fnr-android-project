package com.example.fnr_android_project.network

import com.example.fnr_android_project.model.BaseSetResponse
import com.example.fnr_android_project.model.Set
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/sets")
    fun getSets(): Call<BaseSetResponse>


}