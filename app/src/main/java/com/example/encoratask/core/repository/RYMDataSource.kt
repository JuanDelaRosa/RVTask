package com.example.encoratask.core.repository

import com.example.encoratask.core.common.Result
import com.example.encoratask.core.model.Character
import com.example.encoratask.core.network.api.RyMService
import com.example.encoratask.core.network.mapper.RYMMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RYMDataSource(private val service: RyMService, private val mapper: RYMMapper) {
    suspend fun getList(page : Int): Result<List<Character>> =
        withContext(Dispatchers.IO){
            try{
                val response = service.getList(page)
                if(response.isSuccessful){
                    return@withContext Result.Success(mapper.toList(response.body()))
                }else
                    return@withContext Result.Error(Exception("No se encontro la lista"))
            }catch(e: Exception){
                return@withContext Result.Error(Exception(e.message.toString()))
            }
        }

    suspend fun getCharacter(id:Int): Result<Character> =
        withContext(Dispatchers.IO){
            try{
                val response = service.getCharacter(id)
                if(response.isSuccessful){
                    return@withContext Result.Success(mapper.toCharacter(response.body()))
                }else
                    return@withContext Result.Error(Exception("No se encontro la lista"))
            }catch(e: Exception){
                return@withContext Result.Error(Exception(e.message.toString()))
            }
        }
}