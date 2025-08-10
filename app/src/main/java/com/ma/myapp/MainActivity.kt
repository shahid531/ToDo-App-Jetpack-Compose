package com.ma.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ma.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RandomButton(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun RandomButton(modifier: Modifier) {
    var count by remember { mutableIntStateOf(1) }
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
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
                            if(count > 0)count--
                        }
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text("-", style = TextStyle(fontSize = 28.sp))
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "$count", style = TextStyle(fontSize = 28.sp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable{
                            if (count < 10){
                                count++;
                            }
                        }
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", style = TextStyle(fontSize = 28.sp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RandomButtonPreview() {
}