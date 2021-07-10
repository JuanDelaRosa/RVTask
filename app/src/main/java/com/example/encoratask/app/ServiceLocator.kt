package com.example.encoratask.app

import com.example.encoratask.core.network.api.NetworkModule
import com.example.encoratask.core.network.mapper.RYMMapper
import com.example.encoratask.core.repository.RYMDataSource

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }
    @Volatile
    var repository: RYMDataSource? = null

    fun provideRepository() : RYMDataSource{
        synchronized(this){
            return repository ?: createRepository()
        }
    }

    private fun createRepository(): RYMDataSource {
        val newRepo =
            RYMDataSource(
                networkModule.createAPI("https://rickandmortyapi.com/api/"),
                RYMMapper()
            )
        repository = newRepo
        return newRepo
    }
}