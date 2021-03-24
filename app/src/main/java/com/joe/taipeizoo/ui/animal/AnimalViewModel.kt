package com.joe.taipeizoo.ui.animal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joe.taipeizoo.bean.animals.AnimalDetailResult

class AnimalViewModel : ViewModel() {
    val animalInfo = MutableLiveData<AnimalDetailResult>()

    fun setItem(info : AnimalDetailResult)
    {
        animalInfo.value = info
    }
}