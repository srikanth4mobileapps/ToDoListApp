package com.sri.todolistapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1)
abstract class ToDoListDataBase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoListDataBase? = null

        fun getDatabase(context: Context): ToDoListDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ToDoListDataBase::class.java,
                    "todo_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}