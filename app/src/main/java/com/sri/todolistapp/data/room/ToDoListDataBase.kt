package com.sri.todolistapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TodoItem::class], version = 1)
abstract class ToDoListDataBase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
