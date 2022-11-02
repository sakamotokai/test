package com.example.learningmwwp

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.db.Daodb
import com.example.learningmwwp.db.Database
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun initDatabase(application: Application): Daodb {
        return Database.getInstance(application).getDao()
    }

    fun setRecyclerData(adapter: MainRecyclerAdapter, list: List<Modeldb>) {
        adapter.setList(list)
    }

    fun addElement(text:String,dialog: BottomSheetDialog){
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).insert(
                Modeldb(
                    text = text
                )
            )
        }
        dialog.findViewById<EditText>(R.id.aboutFragmentEditText)!!.setText(text)
    }
}