package com.clickbus.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clickbus.app.ui.screens.*
import com.clickbus.app.ui.theme.ClickbusAppTheme
import com.clickbus.app.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickbusAppTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel()
                var showBottomBar by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                    label = { Text("Viagens") },
                                    selected = true,
                                    onClick = { navController.navigate("dashboard") }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Info, contentDescription = "Ajuda") },
                                    label = { Text("Suporte") },
                                    selected = false,
                                    onClick = { navController.navigate("support") }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            showBottomBar = false
                            LoginScreen(
                                viewModel = authViewModel,
                                onLoginSuccess = {
                                    navController.navigate("dashboard") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onNavigateToRegister = {
                                    navController.navigate("register")
                                }
                            )
                        }
                        composable("register") {
                            showBottomBar = false
                            RegisterScreen(
                                viewModel = authViewModel,
                                onRegisterSuccess = {
                                    navController.navigate("dashboard") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable("dashboard") {
                            showBottomBar = true
                            DashboardScreen(
                                viewModel = authViewModel,
                                onStartNavigation = {
                                    navController.navigate("ra_module")
                                }
                            )
                        }
                        composable("ra_module") {
                            showBottomBar = false
                            RaContainerScreen(
                                viewModel = authViewModel,
                                onExit = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable("support") {
                            showBottomBar = true
                            SupportScreen(viewModel = authViewModel)
                        }
                    }
                }
            }
        }
    }
}
