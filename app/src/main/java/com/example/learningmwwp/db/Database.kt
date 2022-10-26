package com.example.learningmwwp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


@androidx.room.Database(entities = [Modeldb::class], version = 1)
abstract class Database:RoomDatabase() {
    abstract fun getDao():Daodb
    companion object{
        private var database:Database ?= null
        @Synchronized
        fun getInstance(context: Context):Database{
            return if(database == null){
                database = Room.databaseBuilder(context,Database::class.java,"db").build()
                database as Database
            } else{
                database as Database
            }
        }
    }
}