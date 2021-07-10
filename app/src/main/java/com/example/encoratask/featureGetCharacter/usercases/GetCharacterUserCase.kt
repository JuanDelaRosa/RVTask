package com.example.encoratask.featureGetCharacter.usercases

import com.example.encoratask.core.repository.RYMDataSource

class GetCharacterUserCase(private val repository: RYMDataSource) {
    suspend operator fun invoke(id : Int) = repository.getCharacter(id)
}