package com.joe.taipeizoo.ui.field

import com.joe.taipeizoo.APIInterface.RetrofitManager
import com.joe.taipeizoo.bean.animals.AnimalDetailResult
import com.joe.taipeizoo.ui.home.IHomeRepository
import com.joe.taipeizoo.bean.field.FieldDetailResult

/**
 * author: Joe Cheng
 */
class FieldRepository : IFieldRepository {
    override suspend fun loadDataFromAPI(): List<AnimalDetailResult> {
        val apiInterface = RetrofitManager.instance.apiInterface
        val response = apiInterface.animalInfo.await()
        return response.result.results
    }

    override suspend fun loadDataFromDB() {
        TODO("Not yet implemented")
    }

}