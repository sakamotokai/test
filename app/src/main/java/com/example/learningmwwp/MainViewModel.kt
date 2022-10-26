package com.example.learningmwwp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.db.Daodb
import com.example.learningmwwp.db.Database
import com.example.learningmwwp.db.Modeldb

class MainViewModel:ViewModel() {

    fun initDatabase(application: Application):Daodb{
        return Database.getInstance(application).getDao()
    }

    fun setRecyclerData(adapter:MainRecyclerAdapter,list:List<Modeldb>){
        adapter.setList(list)
    }
}