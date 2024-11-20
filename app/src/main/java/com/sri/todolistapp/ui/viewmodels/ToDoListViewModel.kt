package com.sri.todolistapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.todolistapp.data.room.TodoItem
import com.sri.todolistapp.domain.repositoryImpl.TodoRepositoryImpl
import com.sri.todolistapp.utils.EventMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repositoryImpl: TodoRepositoryImpl,
    private val eventMediator: EventMediator
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val todoList: StateFlow<List<TodoItem>> = _searchQuery
        .debounce(2000)
        .flatMapLatest { query ->
            repositoryImpl.searchTodoItems(query)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun updateSearchQuery(query: String) {
        Log.d("ToDoListViewModel", "Updating search query: $query")
        _searchQuery.value = query
    }

}
