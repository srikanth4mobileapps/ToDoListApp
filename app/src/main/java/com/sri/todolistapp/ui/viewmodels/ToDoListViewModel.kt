package com.sri.todolistapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sri.todolistapp.data.room.ToDoListDataBase
import com.sri.todolistapp.data.room.TodoItem
import com.sri.todolistapp.domain.repositoryImpl.TodoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ToDoListViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl: TodoRepositoryImpl

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val todoItems: StateFlow<List<TodoItem>>

    init {
        val dao = ToDoListDataBase.getDatabase(application).todoDao()
        repositoryImpl = TodoRepositoryImpl(dao)

        todoItems = _searchQuery
            .debounce(2000)
            .flatMapLatest { query -> repositoryImpl.searchTodoItems(query) }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun addTodoItem(todoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repositoryImpl.insertTodoItem(todoItem)
    }
}
