package com.ma.myapp.model

data class RandomToDoModelItem(
    val completed: Boolean,
    val id: Int,
    val isDeleted: Boolean,
    val todo: String,
    val userId: Int
)