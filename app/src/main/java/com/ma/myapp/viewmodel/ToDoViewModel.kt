package com.ma.myapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ma.myapp.model.RandomToDoModelItem
import com.ma.myapp.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _randomToDoList = MutableStateFlow<List<RandomToDoModelItem>>(emptyList())
    val randomToDoList: StateFlow<List<RandomToDoModelItem>> = _randomToDoList

    init {
        val count = savedStateHandle.get<String>("randomNum") // default
        randomToDo(count?.toInt() ?: 0)
    }

    fun randomToDo(id: Int) {
        viewModelScope.launch {
            toDoRepository.randomToDo(id)
                .collectLatest {
                    it.let {
                        _randomToDoList.value = it.body()!!
                    }
                }
        }
    }

    fun deleteToDo(id: Int, index: Int) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(id)
                .collectLatest { response ->
                    _randomToDoList.value = _randomToDoList.value.toMutableList().apply {
                        // this[index] = response.body() ?: return@collectLatest
                        this.removeAt(index)
                    }
                }
        }
    }

    fun completedToDo(id: Int, index: Int) {
        viewModelScope.launch {
            toDoRepository.completeToDo(id, mapOf("completed" to true))
                .collectLatest { response ->
                    _randomToDoList.value = _randomToDoList.value.toMutableList().apply {
                        this[index] = response.body() ?: return@collectLatest
                    }
                }
        }
    }
}