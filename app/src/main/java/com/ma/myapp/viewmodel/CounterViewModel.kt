package com.ma.myapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CounterViewModel : ViewModel() {
    private val _counter = MutableStateFlow<Int>(1)
    val counter: StateFlow<Int> = _counter
    fun incrementCounter() {
        if (_counter.value < 10) _counter.value++


    }

    fun decrementCounter() {
        if (_counter.value > 0) _counter.value--
    }
}