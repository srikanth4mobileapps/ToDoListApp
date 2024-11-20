package com.sri.todolistapp.di

import android.content.Context
import androidx.room.Room
import com.sri.todolistapp.data.repository.TodoRepository
import com.sri.todolistapp.data.room.ToDoListDataBase
import com.sri.todolistapp.data.room.TodoDao
import com.sri.todolistapp.domain.repositoryImpl.TodoRepositoryImpl
import com.sri.todolistapp.utils.EventMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide the Room database instance
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ToDoListDataBase {
        return Room.databaseBuilder(
            context,
            ToDoListDataBase::class.java,
            "todo_database"
        ).build()
    }

    // Provide the TodoDao from the database
    @Provides
    @Singleton
    fun provideTodoDao(database: ToDoListDataBase): TodoDao {
        return database.todoDao()
    }

    // Provide the TodoRepository instance
    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }

    //  provide EventMediator
    @Provides
    @Singleton
    fun provideEventMediator(): EventMediator {
        return EventMediator()
    }
}
