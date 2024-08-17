package com.example.androidtodoroom.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtodoroom.model.TodoEntity
import com.example.androidtodoroom.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    // SOT
    val todoItem = MutableStateFlow<Flow<List<TodoEntity>>>(emptyFlow())
    val todoText = MutableStateFlow("")

    init {
        todoItem.value = repository.getAllTodo()
    }


    fun onTextChange(string: String) {
        todoText.value = string
    }

    fun insert() {
        if (todoText.value.isEmpty()) return
        viewModelScope.launch { repository.insert(listOf(TodoEntity(title = todoText.value))) }
        todoText.value = ""
    }

    fun update(item: TodoEntity) {
        viewModelScope.launch { repository.update(item.copy(isComplete = !item.isComplete)) }
    }
}