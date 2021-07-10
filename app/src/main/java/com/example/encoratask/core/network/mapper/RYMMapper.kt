package com.example.encoratask.core.network.mapper

import com.example.encoratask.core.reponses.CharacterResponse
import com.example.encoratask.featureGetList.responses.CharacterLResponse
import com.example.encoratask.core.model.Character

class RYMMapper {
    fun toList(response : CharacterLResponse?) : List<Character> =
        response?.let { res ->
            res.results?.let { list ->
                list.map {
                    Character(
                        it.id ?: 0,
                        it.name ?: "",
                        it.status ?: "",
                        it.species ?: "",
                        it.type ?: "",
                        it.gender ?: "",
                        it.image ?: "",
                        it.origin?.name ?: "",
                        it.location?.name ?: ""
                    )
                }

            }
        } ?: emptyList()

    fun toCharacter(response : CharacterResponse?) : Character =
        response?.let {
            Character(
                it.id ?: 0,
                it.name ?: "",
                it.status ?: "",
                it.species ?: "",
                it.type ?: "",
                it.gender ?: "",
                it.image ?: "",
                it.origin?.name ?: "",
                it.location?.name ?: ""
            )
        } ?: Character(0,"","","","","","","","")
}