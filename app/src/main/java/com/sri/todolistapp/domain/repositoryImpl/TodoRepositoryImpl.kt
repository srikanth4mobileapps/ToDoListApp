package com.sri.todolistapp.domain.repositoryImpl

import android.util.Log
import com.sri.todolistapp.data.repository.TodoRepository
import com.sri.todolistapp.data.room.TodoDao
import com.sri.todolistapp.data.room.TodoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {

    // Search TodoItems based on query
    override fun searchTodoItems(query: String): Flow<List<TodoItem>> {
        Log.d("TodoRepositoryImpl", "Searching for todos with query: $query")
        return dao.searchTodoItems(query)
    }

    // Insert a TodoItem into the database
    override suspend fun insertTodoItem(todoItem: TodoItem) {
        Log.d("TodoRepositoryImpl", "Inserting TodoItem into DB: $todoItem")

        try {
            dao.insertTodoItem(todoItem)
            Log.d("TodoRepositoryImpl", "TodoItem inserted successfully: $todoItem")
        } catch (e: Exception) {
            Log.e("TodoRepositoryImpl", "Error inserting TodoItem: ${e.message}", e)
        }
    }
}