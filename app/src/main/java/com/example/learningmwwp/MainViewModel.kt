package com.example.learningmwwp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.db.Daodb
import com.example.learningmwwp.db.Database
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun initDatabase(application: Application): Daodb {
        return Database.getInstance(application).getDao()
    }

    fun setRecyclerData(adapter: MainRecyclerAdapter, list: List<Modeldb>) {
        adapter.setList(list)
    }

    fun changeLayoutManager(rec:RecyclerView,context: Context){
        if(rec.layoutManager == mainRecyclerLayoutManager) {
            rec.layoutManager = LinearLayoutManager(context)
        }
        else {
            mainRecyclerLayoutManager = GridLayoutManager(context,2)
            rec.layoutManager = mainRecyclerLayoutManager
        }
    }

    fun initSharedPreferences(mainActivity: MainActivity){
        val preferences = mainActivity.getSharedPreferences("settings",Context.MODE_PRIVATE)
    }

    fun getSharedData(key:String){

    }

    fun addElement(text:String){
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).insert(
                Modeldb(
                    text = text
                )
            )
        }
    }
    fun updateElement(text:String,position:Int){
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).update(Modeldb(id = position,text = text ))
        }
    }
}