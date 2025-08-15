package com.ma.myapp.repository

import com.ma.myapp.model.RandomToDoModelItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ToDoRepository {

    suspend fun randomToDo(id: Int): Flow<Response<List<RandomToDoModelItem>>>
    suspend fun deleteToDo(id: Int): Flow<Response<RandomToDoModelItem>>

    suspend fun completeToDo(
        id: Int,
        body: Map<String, Boolean>
    ): Flow<Response<RandomToDoModelItem>>
}