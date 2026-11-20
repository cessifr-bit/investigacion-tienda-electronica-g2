package com.example.tienda_electronica.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // 10.0.2.2 es "localhost de tu PC" visto desde el emulador de Android.
    // Con un celular físico usa la IP de tu PC, por ejemplo:
    // "http://192.168.1.50:8080/api/"  (debe terminar en "/")
   // const val BASE_URL = "http://10.0.2.2:8080/api/"
    //const val BASE_URL = "http://192.168.101"
    const val BASE_URL ="http://192.168.1.17:8080/api/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    val api: ProductoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductoApi::class.java)
    }
}