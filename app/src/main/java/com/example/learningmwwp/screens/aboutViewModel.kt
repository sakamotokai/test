package com.example.learningmwwp.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class aboutViewModel:ViewModel() {
    private var liveData = MutableLiveData<String>()
    var pLiveData = liveData
    fun setData(data:String){
        liveData.value = data
    }

    fun putData():String{
        return pLiveData.value.toString()
    }


    fun addElement(modeldb: Modeldb){
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization().insert(modeldb)
        }
    }

    fun setMainData(activity: MainActivity,data:String,position: Int){
        val i = activity
        i.recAdapter.insertData(data,position)
    }
}