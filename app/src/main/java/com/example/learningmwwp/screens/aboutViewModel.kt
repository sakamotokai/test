package com.example.learningmwwp.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.checker
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import com.example.learningmwwp.globalDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class aboutViewModel:ViewModel() {

    fun addElement(modeldb: Modeldb){
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).insert(modeldb)
        }
    }

    fun addLogic(modeldb: Modeldb){
        if(checker){
            checker = false
        }
        else{
            viewModelScope.launch(Dispatchers.IO) {
                RepositoryRealization(globalDao).insert(modeldb)
            }
        }
        checker = false
    }
}