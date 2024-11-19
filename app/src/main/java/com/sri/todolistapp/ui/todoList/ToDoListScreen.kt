package com.sri.todolistapp.ui.todoList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sri.todolistapp.R
import com.sri.todolistapp.ui.theme.floatingActionButtonColor
import com.sri.todolistapp.ui.theme.toolbarColor
import com.sri.todolistapp.ui.viewmodels.ToDoListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(navController: NavController, viewModel: ToDoListViewModel) {
    val todoItems by viewModel.todoItems.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ToDo List App",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = toolbarColor
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                contentColor = Color.Black,
                containerColor = floatingActionButtonColor,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_todo))
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { contentPadding ->

        Column {
            ElevatedCard(modifier = Modifier.padding(contentPadding)) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    singleLine = true,
                    value = searchQuery,
                    colors = TextFieldDefaults.colors(
                        cursorColor = toolbarColor,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = {
                        Text(
                            text = "Search TODOs",
                            color = Color.LightGray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
            }

            if (searchQuery.isEmpty() && todoItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.empty_screen_text))
                }
            } else {
                LazyColumn {
                    items(todoItems) { item ->
                        ElevatedCard(
                            modifier = Modifier.padding(
                                start = 8.dp,
                                end = 8.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                text = item.text,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Justify,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                }
            }
        }
    }

}
