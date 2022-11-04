package com.example.learningmwwp

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningmwwp.db.Daodb

lateinit var globalDao:Daodb
lateinit var mainRecyclerLayoutManager:RecyclerView.LayoutManager
enum class layoutManagerMode(number: Int){
    LINEAR(0),
    GRID(1)
}