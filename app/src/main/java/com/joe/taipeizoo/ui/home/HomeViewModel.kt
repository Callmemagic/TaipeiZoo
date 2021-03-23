package com.joe.taipeizoo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.joe.taipeizoo.APIInterface.RetrofitManager
import com.joe.taipeizoo.bean.Field.Result
import com.joe.taipeizoo.bean.Field.ResultX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import com.joe.taipeizoo.bean.Field.Field
import java.net.URL

class HomeViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
    val fields = MutableLiveData<Field>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val apiInterface = RetrofitManager.instance.apiInterface
            val response = apiInterface.fieldInfo.await()
            Log.d("IO層", "${response.result.count}")
            fields.postValue(response)
        }
    }

}