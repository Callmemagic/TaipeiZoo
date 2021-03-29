package com.joe.taipeizoo.ui.home

import com.joe.taipeizoo.APIInterface.RetrofitManager
import com.joe.taipeizoo.bean.field.FieldDetailResult

/**
 * author: Joe Cheng
 */
class HomeRepository : IHomeRepository {
    override suspend fun loadDataFromAPI() : List<FieldDetailResult>{
        val apiInterface = RetrofitManager.instance.apiInterface
        val response = apiInterface.fieldInfo.await()
        return response.result.results
    }

    override suspend fun loadDataFromDB() {
        TODO("Not yet implemented")
    }
}