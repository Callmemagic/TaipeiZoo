package com.joe.taipeizoo.ui.home

import androidx.lifecycle.ViewModelProvider
import com.joe.taipeizoo.bean.field.FieldDetailResult

/**
 * author: Joe Cheng
 */
interface IHomeRepository {
    suspend fun loadDataFromAPI(): List<FieldDetailResult>?
    suspend fun loadDataFromDB()
}