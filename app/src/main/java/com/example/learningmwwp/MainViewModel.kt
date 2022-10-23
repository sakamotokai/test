package com.example.learningmwwp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private var liveData = MutableLiveData<String>()
    var pLiveData = liveData
    fun setLiveData(data:String){
        liveData.value = data
    }

    fun putData():String{
        return liveData.value.toString()
    }

    fun setOwerData(data:String){
        setLiveData(data)
    }
}