package com.joe.taipeizoo.ui.field

import androidx.lifecycle.ViewModelProvider
import com.joe.taipeizoo.bean.animals.AnimalDetailResult
import com.joe.taipeizoo.bean.field.FieldDetailResult

/**
 * author: Joe Cheng
 */
interface IFieldRepository {
    suspend fun loadDataFromAPI(): List<AnimalDetailResult>?
    suspend fun loadDataFromDB()
}