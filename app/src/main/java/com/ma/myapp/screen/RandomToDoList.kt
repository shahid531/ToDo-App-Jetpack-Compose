package com.ma.myapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ma.myapp.viewmodel.ToDoViewModel

@Composable
fun ToDoList(modifier: Modifier = Modifier, toDoViewModel: ToDoViewModel = hiltViewModel()) {

    val state = toDoViewModel.randomToDoList.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),

        ) {
        items(state.value.size) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(if (state.value[it].completed) 0xFF77DD77 else 0xFFFFA500),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 20.dp)
                ) {
                    Checkbox(
                        checked = state.value[it].completed, onCheckedChange = { _ ->
                            println("CHECKED")
                            if (!state.value[it].completed) {
                                toDoViewModel.completedToDo(state.value[it].id, index = it)
                            }
                        },
                        modifier = Modifier.size(24.dp),
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF4CAF50),
                            uncheckedColor = Color(0xFF9E9E9E),
                            checkmarkColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        state.value[it].todo, color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Default.Delete,
                        "delete todo",
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.clickable {
                            if (!state.value[it].completed) {
                                toDoViewModel.deleteToDo(state.value[it].id, it)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToDoListPreview() {
    ToDoList()
}