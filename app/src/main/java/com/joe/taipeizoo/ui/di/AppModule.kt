package com.joe.taipeizoo.ui.di

import com.joe.taipeizoo.ui.field.FieldRepository
import com.joe.taipeizoo.ui.field.FieldViewModel
import com.joe.taipeizoo.ui.home.HomeRepository
import com.joe.taipeizoo.ui.home.HomeViewModel
import org.koin.dsl.module

/**
 * author: Joe Cheng
 */
val appModule = module {
    single {
        val homeRepository = HomeRepository()
        HomeViewModel(homeRepository)
    }
    single {
        val fieldRepository = FieldRepository()
        FieldViewModel(fieldRepository)
    }
}