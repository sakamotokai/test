package com.example.learningmwwp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.RecyclerAdapter.MainRecyclerAdapter
import com.example.learningmwwp.databinding.ActivityMainBinding
import com.example.learningmwwp.screens.aboutFragment

class MainActivity : AppCompatActivity() {
    lateinit var rec :RecyclerView
    lateinit var recAdapter:MainRecyclerAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recycler(viewModel)
        clickListener()
        viewModel.pLiveData.observe(this, Observer {
            binding.mainText.text = it
            recAdapter.addElement(it)
        })
    }

    fun clickListener() {
        binding.mainAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.apply {
                putString("text","")
                putInt("position",0)
            }
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainer, aboutFragment.getNewInstance(bundle)).commit()
        }
    }

    fun recycler(viewModel: MainViewModel) {
        rec = binding.mainRecyclerView
        recAdapter = MainRecyclerAdapter()
        rec.adapter = recAdapter
        rec.layoutManager = GridLayoutManager(applicationContext, 2)
        recAdapter.addElement("first")
        recAdapter.addElement("second")
        recAdapter.addElement("thread")
    }
}