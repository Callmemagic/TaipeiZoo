package com.joe.taipeizoo.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.taipeizoo.APIInterface.RetrofitManager
import com.joe.taipeizoo.bean.animals.AnimalDetailResult
import com.joe.taipeizoo.bean.field.FieldDetailResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class SecondInfoViewModel : ViewModel() {
    val itemClicked = MutableLiveData<FieldDetailResult>()

    val results = MutableLiveData<List<AnimalDetailResult>>()
    val clickItem = MutableLiveData<AnimalDetailResult>()
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text
    fun setItem(item : FieldDetailResult) {
        itemClicked.value = item
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = URL("https://data.taipei/api/v1/dataset/\n" +
                    "a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire").readText()
            Log.d("看文本", "${data}")
            val apiInterface = RetrofitManager.instance.apiInterface
            val response = apiInterface.animalInfo.await()
            results.postValue(response.result.results)
        }
    }

    fun clickItem(item : AnimalDetailResult) {
        clickItem.postValue(item)
    }

}