package com.joe.taipeizoo.ui.field

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * author: Joe Cheng
 */
class FieldViewModelFactory(private val fieldRepository: FieldRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FieldViewModel::class.java))
        {
            return FieldViewModel(fieldRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}