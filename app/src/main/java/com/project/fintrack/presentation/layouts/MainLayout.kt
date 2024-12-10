package com.project.fintrack.presentation.layouts

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.fintrack.data.db.LocalDatabase
import com.project.fintrack.data.models.NavItem
import com.project.fintrack.data.repository.Repository
import com.project.fintrack.presentation.screens.CreateTransactionScreen
import com.project.fintrack.presentation.screens.EditTransactionScreen
import com.project.fintrack.presentation.screens.HomeScreen
import com.project.fintrack.presentation.screens.ReportScreen
import com.project.fintrack.presentation.viewmodels.HomeViewModel
import com.project.fintrack.presentation.viewmodels.TransactionReportViewModel

@Composable
fun MainLayout(modifier: Modifier = Modifier, activity: ComponentActivity) {
    val navController = rememberNavController()
    val navItems = listOf(
        NavItem("Home", Icons.Filled.Home),
        NavItem("Add", Icons.Filled.AddCircle),
        NavItem("Report", Icons.Filled.Info)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            when (index) {
                                0 -> navController.navigate("home")
                                1 -> navController.navigate("createTransaction")
                                2 -> navController.navigate("report")
                            }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            val db = LocalDatabase.getInstance(activity)
            val repository = Repository(db)

            composable("home") {
                HomeScreen(viewModel = HomeViewModel(repository), navController)
            }
            composable("createTransaction") {
                CreateTransactionScreen()
            }
            composable("report") {
                ReportScreen(viewModel = TransactionReportViewModel(repository), navController)
            }
            composable("editTransaction/{transactionId}") { backStackEntry ->
                val transactionId = backStackEntry.arguments?.getString("transactionId")?.toInt()
                if (transactionId != null) {
                    EditTransactionScreen(transactionId = transactionId)
                }
            }
        }
    }
}