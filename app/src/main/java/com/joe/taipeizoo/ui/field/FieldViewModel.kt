package com.joe.taipeizoo.ui.field

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.taipeizoo.APIInterface.RetrofitManager
import com.joe.taipeizoo.bean.animals.AnimalDetailResult
import com.joe.taipeizoo.bean.field.FieldDetailResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel : ViewModel() {
    val fieldClicked = MutableLiveData<FieldDetailResult?>()
    val results = MutableLiveData<List<AnimalDetailResult>>()
    val animalClicked = MutableLiveData<AnimalDetailResult>()
    val dataLoaded = MutableLiveData<Boolean>()
    val onClickOpenWeb = MutableLiveData<String>()
    val isConnected = MutableLiveData<Boolean>()

    init {
        dataLoaded.value = false
    }

    fun setNetworkConnected(connected : Boolean)
    {
        Log.d("Network", "setNetworkConnected: ${connected}")
        isConnected.value = connected
        if(connected)
        {
            getAnimalList()
        }
    }

    private fun getAnimalList()
    {
        if(dataLoaded.value == true) return
        viewModelScope.launch(Dispatchers.IO) {
            val apiInterface = RetrofitManager.instance.apiInterface
            val response = apiInterface.animalInfo.await()
            results.postValue(response.result.results)
            dataLoaded.postValue(true)
        }
    }

    //上一頁帶進來的地點
    fun setItem(item : FieldDetailResult) {
        fieldClicked.value = item
    }

    //點擊到的動物
    fun clickItem(item : AnimalDetailResult) {
        animalClicked.postValue(item)
    }

    fun clearClickItem()
    {
        animalClicked.value = null
    }

    fun openWeb(url: String)
    {
        onClickOpenWeb.value = url
    }

    fun clearOpenWebUrl()
    {
        onClickOpenWeb.value = null
    }
}