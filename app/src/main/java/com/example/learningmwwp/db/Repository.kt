package com.example.learningmwwp.db

import androidx.lifecycle.LiveData

interface Repository {
    val allModelsbd:LiveData<List<Modeldb>>
    suspend fun insert(modeldb: Modeldb)
    suspend fun delete(modeldb: Modeldb)
    suspend fun update(modeldb: Modeldb)
}