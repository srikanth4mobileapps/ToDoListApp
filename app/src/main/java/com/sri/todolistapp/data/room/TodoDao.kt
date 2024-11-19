package com.sri.todolistapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items WHERE text LIKE '%' || :query || '%'")
    fun searchTodoItems(query: String): Flow<List<TodoItem>>

    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem)
}