package com.ma.myapp.repository

import com.ma.myapp.model.RandomToDoModelItem
import com.ma.myapp.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val apiService: ApiService) : ToDoRepository {
    override suspend fun randomToDo(id: Int): Flow<Response<List<RandomToDoModelItem>>> {
        return flow {
            emit(apiService.randomToDo(id))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteToDo(id: Int): Flow<Response<RandomToDoModelItem>> {
        return flow {
            emit(apiService.deleteToDo(id))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun completeToDo(
        id: Int,
        body: Map<String, Boolean>
    ): Flow<Response<RandomToDoModelItem>> {
        return flow {
            emit(apiService.completeToDo(id, body))
        }.flowOn(Dispatchers.IO)
    }
}