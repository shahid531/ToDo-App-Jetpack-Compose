package com.ma.myapp.service

import com.ma.myapp.model.RandomToDoModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("todos/random/{id}")
    suspend fun randomToDo(@Path("id") id: Int): Response<List<RandomToDoModelItem>>

    @DELETE("todos/{id}")
    suspend fun deleteToDo(@Path("id") id: Int): Response<RandomToDoModelItem>

    @PUT("todos/{id}")
    suspend fun completeToDo(
        @Path("id") id: Int,
        @Body body: Map<String, Boolean>
    ): Response<RandomToDoModelItem>
}