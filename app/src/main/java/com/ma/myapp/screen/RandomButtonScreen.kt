package com.ma.myapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ma.myapp.viewmodel.CounterViewModel

@Composable
fun RandomButton(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel(),
    navController: NavController
) {
    val count = counterViewModel.counter.collectAsStateWithLifecycle()
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .width(150.dp)
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(12))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                counterViewModel.decrementCounter()
                            }
                            .weight(1f)
                            .fillMaxHeight()
                            .background(color = Color(0xFFE0E0E0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("-", style = TextStyle(fontSize = 28.sp, color = Color(0xFF333333)))
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${count.value}", style = TextStyle(fontSize = 28.sp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clickable {
                                counterViewModel.incrementCounter()
                            }
                            .weight(1f)
                            .fillMaxHeight()
                            .background(color = Color(0xFFA8D5BA)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+", style = TextStyle(fontSize = 28.sp, color = Color(0xFF333333)))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            ElevatedButton(
                onClick = {
                    navController.navigate("randomListScreen/${count.value}")
                }
            ) {
                Text(text = "Random")
            }
        }
    }
}