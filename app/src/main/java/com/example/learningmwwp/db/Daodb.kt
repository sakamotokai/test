package com.example.learningmwwp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Daodb {
    @Insert
    suspend fun insert(modeldb: Modeldb)
    @Delete
    suspend fun delete(modeldb: Modeldb)
    @Update
    suspend fun update(modeldb: Modeldb)
    @Query("SELECT*FROM note")
    fun getAllModelsdb():LiveData<List<Modeldb>>
}