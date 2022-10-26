package com.example.learningmwwp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.db.Daodb
import com.example.learningmwwp.db.Database

class MainViewModel:ViewModel() {
    private var liveData = MutableLiveData<String>()
    private var recyclerLiveData = MutableLiveData<List<String>>()
    private val recAdapter = MainRecyclerAdapter()
    var pRecAdapter = recAdapter
    var pRecyclerLiveData = recyclerLiveData
    var pLiveData = liveData

    fun setRecyclerLiveData(list:List<String>){
        recAdapter.setData(list)
    }

    fun initDatabase(application: Application){
        val dao = Database.getInstance(application).getDao()
    }

    fun setDefaultRecyclerLiveData():MainRecyclerAdapter{
        recAdapter.apply {
            addElement("First")
            addElement("Second")
        }
        return recAdapter
    }

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