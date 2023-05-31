package com.example.elclarin

import retrofit2.Call
import retrofit2.http.GET

interface ConsumirApi {
    @GET("top-headlines?country=mx&apiKey=f8632a6b5f1c4100b37268010b414dda")
    fun getTraer(): Call<Noticias>
}