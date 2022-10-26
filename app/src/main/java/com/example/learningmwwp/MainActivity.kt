package com.example.learningmwwp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.databinding.ActivityMainBinding
import com.example.learningmwwp.db.RepositoryRealization
import com.example.learningmwwp.screens.aboutFragment

class MainActivity : AppCompatActivity() {
    lateinit var rec: RecyclerView
    lateinit var recAdapter: MainRecyclerAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        globalDao = viewModel.initDatabase(application = application)
        recycler(binding)
        clickListener()
        RepositoryRealization(globalDao).allModelsbd.observe(this, Observer {
            viewModel.setRecyclerData(recAdapter, it)
        })
    }


    fun clickListener() {
        binding.mainAdd.setOnClickListener {
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainer, aboutFragment()).commit()
        }
    }

    //Создание recyclerView
    fun recycler(binding: ActivityMainBinding) {
        rec = binding.mainRecyclerView
        recAdapter = MainRecyclerAdapter()
        rec.adapter = recAdapter
        rec.layoutManager = GridLayoutManager(applicationContext, 2)
    }
}