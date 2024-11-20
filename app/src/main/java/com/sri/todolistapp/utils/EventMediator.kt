package com.sri.todolistapp.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

sealed class ToDoListScreenErrorEvent {
    data class Error(val message: String) : ToDoListScreenErrorEvent()
}

class EventMediator @Inject constructor() {
    private val _eventChannel = Channel<ToDoListScreenErrorEvent>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

    suspend fun sendEvent(event: ToDoListScreenErrorEvent) {
        _eventChannel.send(event)
    }
}