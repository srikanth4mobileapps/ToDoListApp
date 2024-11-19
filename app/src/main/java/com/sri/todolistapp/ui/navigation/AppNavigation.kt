package com.sri.todolistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sri.todolistapp.ui.addItem.AddItemScreen
import com.sri.todolistapp.ui.todoList.ToDoListScreen
import com.sri.todolistapp.ui.viewmodels.ToDoListViewModel

@Composable
fun AppNavigation(viewModel: ToDoListViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { ToDoListScreen(navController, viewModel) }
        composable("add") { AddItemScreen(navController, viewModel) }
    }
}