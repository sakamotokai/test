package com.example.learningmwwp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun initDatabase(application: Application): Daodb {
        return Database.getInstance(application).getDao()
    }

    fun setRecyclerData(adapter: MainRecyclerAdapter, list: List<Modeldb>) {
        adapter.setList(list)
    }

    fun setLayoutManagerMode(sharedPreferences: SharedPreferences, recycler: RecyclerView) {
        val value = sharedPreferences.getInt("mainRecyclerLayoutMode", 50)//50==Error
        if (value == 50 || value == layoutManagerMode.GRID.ordinal) {
            sharedPreferences.edit()
                .putInt("mainRecyclerLayoutMode", layoutManagerMode.GRID.ordinal).apply()
            recycler.layoutManager = GridLayoutManager(recycler.context, 2)
        } else {
            sharedPreferences.edit()
                .putInt("mainRecyclerLayoutMode", layoutManagerMode.LINEAR.ordinal).apply()
            recycler.layoutManager = LinearLayoutManager(recycler.context)
        }
    }

    fun changeLayoutManager(rec: RecyclerView, sharedPreferences: SharedPreferences) {
        if (layoutManagerMode.GRID.ordinal == sharedPreferences.getInt(
                "mainRecyclerLayoutMode",
                0
            )
        ) {
            rec.layoutManager = LinearLayoutManager(rec.context)
            sharedPreferences.edit()
                .putInt("mainRecyclerLayoutMode", layoutManagerMode.LINEAR.ordinal).apply()
        } else {
            //mainRecyclerLayoutManager = GridLayoutManager(context,2)
            rec.layoutManager = GridLayoutManager(rec.context, 2)
            sharedPreferences.edit()
                .putInt("mainRecyclerLayoutMode", layoutManagerMode.GRID.ordinal).apply()
        }
    }

    fun addElement(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).insert(
                Modeldb(
                    text = text
                )
            )
        }
    }
    var checker = false
    fun addNewNote(
        editText: EditText,
        recAdapter: MainRecyclerAdapter,
        deleteBtn: FloatingActionButton
    ) {

        if (editText.text.toString().isEmpty()) {
            Toast.makeText(editText.context, "Введите текст", Toast.LENGTH_SHORT).show()
        }
        else if (!(editText.text.toString().isEmpty()) && checker) {
            updateElement(editText.text.toString(), recAdapter.getFirstId())
        }else {
            addElement(editText.text.toString())
            deleteBtn.isVisible = true
            checker = true
        }
    }

    fun updateElement(text: String, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).update(Modeldb(id = position, text = text))
        }
    }

    fun deleteElement(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).delete(Modeldb(id = position))
        }
    }
}