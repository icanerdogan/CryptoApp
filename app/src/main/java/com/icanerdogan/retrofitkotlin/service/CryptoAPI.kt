package com.icanerdogan.retrofitkotlin.service

import com.icanerdogan.retrofitkotlin.model.Crypto
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    @GET("assets/?apikey=8A010544-B3AC-45BD-A1F8-298803C8C64B")
    fun getAllData(): Call<List<Crypto>>

}