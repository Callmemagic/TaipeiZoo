package com.joe.taipeizoo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * author: Joe Cheng
 */
class HomeViewModelFactory(private val homeRepository: HomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
        {
            return HomeViewModel(homeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}