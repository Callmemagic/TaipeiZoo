package com.joe.taipeizoo.APIInterface

import com.joe.taipeizoo.bean.animals.Animal
import com.joe.taipeizoo.bean.field.Field
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * author: Joe Cheng
 */
//https://data.taipei/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire
interface APIInterface {
    //寫所有連線的地方，定義func()，實作交給別人
    @get:GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    val fieldInfo: Deferred<Field>

    @get:GET("a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    val animalInfo: Deferred<Animal>
}