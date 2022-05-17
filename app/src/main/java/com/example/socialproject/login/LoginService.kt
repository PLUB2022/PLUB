package com.example.socialproject.login

import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @GET("/login/oauth2/naver")
    fun getNaverLogin(
        @Header("Authorization") Authorization : String
    ): Call<String>
}