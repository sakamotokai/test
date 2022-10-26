package com.example.learningmwwp.db

import androidx.lifecycle.LiveData

class RepositoryRealization(private var daodb: Daodb):Repository {
    override val allModelsbd: LiveData<List<Modeldb>>
        get() = daodb.getAllModelsdb()

    override suspend fun insert(modeldb: Modeldb) {
        daodb.insert(modeldb)
    }

    override suspend fun delete(modeldb: Modeldb) {
        daodb.delete(modeldb)
    }
}