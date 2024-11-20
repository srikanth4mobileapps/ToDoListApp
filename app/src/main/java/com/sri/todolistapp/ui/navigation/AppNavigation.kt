package com.sri.todolistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sri.todolistapp.ui.addItem.AddItemScreen
import com.sri.todolistapp.ui.todoList.ToDoListScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.sri.todolistapp.ui.viewmodels.AddItemViewModel
import com.sri.todolistapp.ui.viewmodels.ToDoListViewModel

@Composable
fun AppNavigation() {

    val toDoListViewModel: ToDoListViewModel = hiltViewModel()
    val addTodoViewModel: AddItemViewModel = hiltViewModel()

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { ToDoListScreen(navController, viewModel = toDoListViewModel) }
        composable("add") { AddItemScreen(navController, viewModel = addTodoViewModel) }
    }
}