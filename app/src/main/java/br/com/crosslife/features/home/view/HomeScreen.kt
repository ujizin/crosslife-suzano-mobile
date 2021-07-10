package br.com.crosslife.features.home.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavController.HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigation {
            }
        }
    ){

    }
}