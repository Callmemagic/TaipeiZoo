package com.joe.taipeizoo.APIInterface

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.joe.taipeizoo.ConstantValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: Joe Cheng
 */
class RetrofitManager private constructor() {
    val apiInterface: APIInterface

    companion object {
        val instance = RetrofitManager()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ConstantValue.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        apiInterface = retrofit.create<APIInterface>(APIInterface::class.java)
    }
}