package com.example.learningmwwp.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.MainViewModel
import java.text.FieldPosition

class ViewModelFragment:ViewModel() {
    private var liveData = MutableLiveData<String>()
    var pLiveData = liveData
    fun setData(data:String){
        liveData.value = data
    }

    fun putData():String{
        return pLiveData.value.toString()
    }

    fun setMainData(activity: MainActivity,data:String,position: Int){
        val i = activity
        i.recAdapter.insertData(data,position)
    }
}