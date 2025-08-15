package com.ma.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ma.myapp.screen.RandomButton
import com.ma.myapp.screen.ToDoList
import com.ma.myapp.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "rounderCounterScreen",
        modifier = modifier
    ) {
        composable("rounderCounterScreen") {
            RandomButton(navController = navController)
        }
        composable("randomListScreen/{randomNum}") { backStackEntry ->

            // val count = backStackEntry.arguments?.getString("randomNum")?.toIntOrNull() ?: 0
            ToDoList()
        }
    }
}