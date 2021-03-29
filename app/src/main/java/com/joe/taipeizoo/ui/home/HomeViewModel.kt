package com.joe.taipeizoo.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.taipeizoo.bean.field.FieldDetailResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val repository: IHomeRepository) : ViewModel() {
    val results = MutableLiveData<List<FieldDetailResult>?>()
    val clickItem = MutableLiveData<FieldDetailResult>()
    val dataLoaded = MutableLiveData<Boolean>()
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
            getFieldList()
        }
    }

    private fun getFieldList()
    {
        if(dataLoaded.value == true) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                results.postValue(repository.loadDataFromAPI())
                dataLoaded.postValue(true)
            } catch (e : Exception)
            {
                e.printStackTrace()
            }
        }
    }

    fun clickItem(item : FieldDetailResult) {
        clickItem.postValue(item)
    }

    fun clearClickItem()
    {
        clickItem.value = null
    }
}