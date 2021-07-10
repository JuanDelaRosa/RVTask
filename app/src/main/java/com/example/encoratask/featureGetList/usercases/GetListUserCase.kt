package com.example.encoratask.featureGetList.usercases

import com.example.encoratask.core.repository.RYMDataSource

class GetListUserCase(private val repository: RYMDataSource) {
    suspend operator fun invoke(page :Int) = repository.getList(page)
}