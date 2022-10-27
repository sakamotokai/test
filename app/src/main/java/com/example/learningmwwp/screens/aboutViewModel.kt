package com.example.learningmwwp.screens

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningmwwp.MainActivity
import com.example.learningmwwp.R
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.checker
import com.example.learningmwwp.databinding.FragmentAboutBinding
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import com.example.learningmwwp.globalDao
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class aboutViewModel : ViewModel() {

    fun addElement(modeldb: Modeldb) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryRealization(globalDao).insert(modeldb)
        }
    }

    fun showDescription(
        modeldb: Modeldb,
        binding: FragmentAboutBinding,
        bundle: Bundle?,
        textView: TextView,
        deleteBtn: FloatingActionButton
    ) {
        if (bundle?.getString("text") != null) {
            val description = bundle.getString("text", "Test Value")
            //binding.aboutFragmentTextView.text = description
            textView.text = description
            //binding.aboutDelete.setOnClickListener {
            deleteBtn.setOnClickListener {
                viewModelScope.launch(Dispatchers.IO) {
                    RepositoryRealization(globalDao).delete(modeldb)
                }
            }
            /*binding.fragmentAdd.setOnClickListener {
                viewModelScope.launch(Dispatchers.IO) {
                    RepositoryRealization(globalDao).update(
                        Modeldb(
                            id = modeldb.id,
                            text = binding.aboutFragmentEditText.text.toString()
                        )
                    )
                }
            }*/
        }
    }

    fun elementId(bundle: Bundle?): Int {
        if (bundle?.getInt("key") == null) {
            return 0
        } else {
            return bundle.getInt("key")
        }
    }

    fun addLogic(
        modeldb: Modeldb,
        binding: FragmentAboutBinding,
        bundle: Bundle?,
        adapter: MainRecyclerAdapter,
        editText: EditText
    ) {
        if (bundle?.getString("text") == null) {
            viewModelScope.launch(Dispatchers.IO) {
                RepositoryRealization(globalDao).insert(modeldb)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                RepositoryRealization(globalDao).update(
                    Modeldb(
                        id = modeldb.id,
                        text = editText.text.toString()//binding.aboutFragmentEditText.text.toString()
                    )
                )
            }
            adapter.changeList(
                Modeldb(
                    id = modeldb.id,
                    text = editText.text.toString()
                )
            )//binding.aboutFragmentEditText.text.toString()))
        }
    }
}