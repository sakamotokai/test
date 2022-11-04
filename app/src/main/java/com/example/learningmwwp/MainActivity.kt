package com.example.learningmwwp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.databinding.ActivityMainBinding
import com.example.learningmwwp.db.Modeldb
import com.example.learningmwwp.db.RepositoryRealization
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var rec: RecyclerView
    lateinit var recAdapter: MainRecyclerAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("settings",Context.MODE_PRIVATE)
        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        globalDao = viewModel.initDatabase(application = application)
        recycler(binding)
        binding.mainAdd.setOnClickListener {
            clickListener()
        }
        RepositoryRealization(globalDao).allModelsbd.observe(this, Observer {
            viewModel.setRecyclerData(recAdapter, it.asReversed())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        supportActionBar!!.setTitle("")
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mainMenuChangeGrig -> {
                viewModel.changeLayoutManager(rec, applicationContext)
            }
        }
        return true
    }

    fun clickListener() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.fragment_about)
        val addBtn = dialog.findViewById<FloatingActionButton>(R.id.aboutAdd)
        val editText = dialog.findViewById<EditText>(R.id.aboutFragmentEditText)
        val deleteBtn = dialog.findViewById<FloatingActionButton>(R.id.aboutDelete)
        var buffer = editText!!.text.toString()
        deleteBtn!!.isVisible = false
        addBtn!!.setOnClickListener {
            if (buffer.isEmpty()) {
                viewModel.addElement(editText.text.toString())
                buffer = editText.text.toString()
            } else {
                viewModel.updateElement(editText.text.toString(),recAdapter.getFirstId())
            }
        }
        dialog.show()
    }

    fun recycler(binding: ActivityMainBinding) {
        rec = binding.mainRecyclerView
        recAdapter = MainRecyclerAdapter()
        rec.adapter = recAdapter
        mainRecyclerLayoutManager = GridLayoutManager(applicationContext, 2)
        //sharedPreferences.edit().putInt("Settings",layoutManagerMode.GRID.ordinal)
        rec.layoutManager = mainRecyclerLayoutManager
    }
}