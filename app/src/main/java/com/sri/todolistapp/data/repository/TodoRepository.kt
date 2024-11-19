package com.sri.todolistapp.data.repository

import com.sri.todolistapp.data.room.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun searchTodoItems(query: String): Flow<List<TodoItem>>
    suspend fun insertTodoItem(todoItem: TodoItem)
}