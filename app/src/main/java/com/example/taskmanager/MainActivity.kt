package com.example.taskmanager

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.viewmodels.LoginViewModel
import com.example.taskmanager.views.AddTaskView
import com.example.taskmanager.views.CalendarScreen
import com.example.taskmanager.views.LoginScreen
import com.example.taskmanager.views.SignupScreen
import androidx.activity.compose.rememberLauncherForActivityResult

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerApp()
            RequestNotificationPermission()
        }
    }
}

@Composable
fun RequestNotificationPermission() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            println("Permissão para notificações concedida.")
        } else {
            println("Permissão para notificações negada.")
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}

@Composable
fun TaskManagerApp() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = if (loginViewModel.isUserLoggedIn()) "calendar" else "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("calendar") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate("signup")
                }
            )
        }
        composable("signup") {
            SignupScreen(onSignupSuccess = {
                navController.navigate("calendar") {
                    popUpTo("signup") { inclusive = true }
                }
            })
        }
        composable("calendar") {
            CalendarScreen(
                onNavigateToAddTask = {
                    navController.navigate("addTask")
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("calendar") { inclusive = true }
                    }
                }
            )
        }
        composable("addTask") {
            AddTaskView(onTaskAdded = {
                navController.popBackStack()

            })
        }
    }
}