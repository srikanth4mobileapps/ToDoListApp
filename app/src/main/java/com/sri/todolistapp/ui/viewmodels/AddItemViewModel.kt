package com.sri.todolistapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sri.todolistapp.data.room.TodoItem
import com.sri.todolistapp.domain.repositoryImpl.TodoRepositoryImpl
import com.sri.todolistapp.utils.EventMediator
import com.sri.todolistapp.utils.ToDoListScreenErrorEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val todoRepositoryImpl: TodoRepositoryImpl,
    private val eventMediator: EventMediator,
) : ViewModel() {

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun updateInputText(text: String) {
        _inputText.value = text
    }

    fun addTodo(onSuccess: () -> Unit, onError: () -> Unit) {
        val text = _inputText.value
        Log.d("AddItemViewModel", "addTodo called with text: $text")
        viewModelScope.launch {
            _isLoading.value = true
            if (text.isBlank()) {
                eventMediator.sendEvent(ToDoListScreenErrorEvent.Error("Please enter a valid TODO item"))
                onError()
            } else {
                try {
                    delay(2000)
                    todoRepositoryImpl.insertTodoItem(
                        TodoItem(
                            text = text
                        )
                    )
                    onSuccess()
                } catch (e: Exception) {
                    Log.e("AddItemViewModel", "Error adding TODO", e)
                    eventMediator.sendEvent(ToDoListScreenErrorEvent.Error("Failed to add TODO"))
                    onError()
                } finally {
                    _inputText.value = ""
                    _isLoading.value = false
                }
            }

        }

    }

}
