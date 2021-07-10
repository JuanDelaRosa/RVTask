package com.example.encoratask.core.network.api

import com.example.encoratask.core.reponses.CharacterResponse
import com.example.encoratask.featureGetList.responses.CharacterLResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RyMService {
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterResponse>
    @GET("character/")
    suspend fun getList(@Query("page") page : Int): Response<CharacterLResponse>
}