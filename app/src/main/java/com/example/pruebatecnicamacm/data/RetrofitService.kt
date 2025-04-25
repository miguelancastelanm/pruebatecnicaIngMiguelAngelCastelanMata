package com.example.pruebatecnicamacm.data

import com.example.pruebatecnicamacm.data.model.RemoteResult
import com.example.pruebatecnicamacm.data.model.Result1Detail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("character")
    suspend fun listDetalle(): RemoteResult

    @GET("character/{type}")
    suspend fun listDetalle1(
      @Path("type") type: String
    ):Result1Detail

}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}