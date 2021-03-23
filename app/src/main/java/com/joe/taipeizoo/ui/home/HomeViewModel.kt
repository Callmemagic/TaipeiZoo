package com.joe.taipeizoo.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.taipeizoo.APIInterface.RetrofitManager
import com.joe.taipeizoo.bean.field.ResultX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.joe.taipeizoo.bean.field.Field

class HomeViewModel : ViewModel() {
    val results = MutableLiveData<List<ResultX>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val apiInterface = RetrofitManager.instance.apiInterface
            val response = apiInterface.fieldInfo.await()
            Log.d("IO層", "${response.result.count}")
            results.postValue(response.result.results)
        }
    }

}