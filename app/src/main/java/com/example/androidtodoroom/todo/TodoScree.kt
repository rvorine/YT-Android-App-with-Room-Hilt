package com.example.androidtodoroom.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TodoScreen() {

    val vm: TodoViewModel = hiltViewModel()
    val todoItems = vm.todoItem.collectAsState().value.collectAsState(initial = emptyList()).value
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)) {
                Row {
                    TextField(
                        value = vm.todoText.collectAsState().value,
                        onValueChange = { str -> vm.onTextChange(str) },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedButton(
                        onClick = { vm.insert() },
                        modifier = Modifier.size(50.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Today's Todo",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(todoItems) { todo ->
                        Row {
                            Checkbox(
                                checked = todo.isComplete,
                                onCheckedChange = { _ -> vm.update(todo) }
                            )
                            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                                Text(
                                    text = todo.title,
                                    style = TextStyle(textDecoration = if (todo.isComplete) TextDecoration.LineThrough else TextDecoration.None)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}