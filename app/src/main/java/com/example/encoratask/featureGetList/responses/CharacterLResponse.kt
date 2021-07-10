package com.example.encoratask.featureGetList.responses

import com.example.encoratask.core.reponses.CharacterResponse
import com.example.encoratask.core.reponses.Info

data class CharacterLResponse(
    val info: Info?,
    val results: List<CharacterResponse>?
)