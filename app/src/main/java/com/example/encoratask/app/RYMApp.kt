package com.example.encoratask.app

import android.app.Application
import com.example.encoratask.core.repository.RYMDataSource
import com.example.encoratask.featureGetCharacter.usercases.GetCharacterUserCase
import com.example.encoratask.featureGetList.usercases.GetListUserCase

class RYMApp : Application() {
    private val repository : RYMDataSource
    get() = ServiceLocator.provideRepository()

    val getList : GetListUserCase
    get() = GetListUserCase(repository)

    val getCharacter : GetCharacterUserCase
    get() = GetCharacterUserCase(repository)
}