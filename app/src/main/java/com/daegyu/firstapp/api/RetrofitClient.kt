package com.daegyu.firstapp.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

        // HTTP 요청 로그 출력
        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 로그 출력 범위
        }

        // 네트워크 요청 수행 객체
        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // JSON 형태를 객체로 매핑시켜 변환하는 객체
        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        // 레트로핏
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.aladin.co.kr/ttb/")
            .addConverterFactory(GsonConverterFactory.create(gson)) // JSON
            .client(client)
            .build()

        // 레트로핏을 통해 인터페이스를 구현한 객체 생성
        val bookApiService: BookApiService = retrofit.create(BookApiService::class.java)

}