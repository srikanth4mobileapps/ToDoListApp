package com.sri.todolistapp.domain.repositoryImpl

import com.sri.todolistapp.data.repository.TodoRepository
import com.sri.todolistapp.data.room.TodoDao
import com.sri.todolistapp.data.room.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {
    override fun searchTodoItems(query: String): Flow<List<TodoItem>> {
        return dao.searchTodoItems(query)
    }

    override suspend fun insertTodoItem(todoItem: TodoItem) {
        dao.insertTodoItem(todoItem)
    }
}