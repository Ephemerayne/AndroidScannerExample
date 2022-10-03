package com.example.androidscannerexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidscannerexample.ui.theme.AndroidScannerExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidScannerExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    val scannerTypeParameters = listOf(
                        ScannerTypeParameters(
                            title = "Pacs",
                            hint = "",
                            onScanned = {
                                navController.navigate("pacs?barCode=$it")
                            },
                            action = { ScannerPacsAction() }
                        ),
                        ScannerTypeParameters(
                            title = "Products",
                            hint = "",
                            onScanned = {
                                navController.popBackStack()
                                navController.navigate("products?barCode=$it")
                            },
                            action = {
                                Column {
                                    ScannerProductsAction()
                                    ScannerProductsAction2()
                                }
                            }
                        ),
                    )

                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }

                        composable("scanner") { ScannerScreen(scannerTypeParameters) }

                        composable(
                            "pacs?barCode={barCode}",
                            arguments = listOf(
                                navArgument("barCode") { type = NavType.StringType }
                            )
                        ) {
                            PacsScreen(it.arguments?.getString("barCode") ?: "error")
                        }

                        composable(
                            "products?barCode={barCode}",
                            arguments = listOf(
                                navArgument("barCode") { type = NavType.StringType }
                            )
                        ) {
                            ProductsScreen(it.arguments?.getString("barCode") ?: "error")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ScannerPacsAction() {
    Button(
        onClick = { println("ScannerPacsAction") }
    ) {
        Text(text = "ScannerPacsAction")
    }
}

@Composable
fun ScannerProductsAction() {
    Button(
        onClick = { println("ScannerProductsAction") }
    ) {
        Text(text = "ScannerProductsAction")
    }
}

@Composable
fun ScannerProductsAction2() {
    Button(
        onClick = { println("ScannerProductsAction2") },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Green,
            contentColor = Color.White
        ),
    ) {
        Text(text = "ScannerProductsAction2")
    }
}

@Composable
fun MainScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OpenScanner { navHostController.navigate("scanner") }
    }
}

@Composable
fun OpenScanner(onClick: () -> Unit) {
    Button(
        onClick = onClick,
    ) {
        Text(text = "Open Scanner")
    }
}
