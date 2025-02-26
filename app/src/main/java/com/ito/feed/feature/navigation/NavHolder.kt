package com.ito.feed.feature.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ito.feed.feature.feed.FeedsScreen
import com.ito.feed.feature.login.LoginScreen
import com.ito.feed.ui.theme.FeedTheme

@Composable
fun NavHolder() {
    val navController = rememberNavController()

    FeedTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route
            ) {
                composable(Screen.Login.route) { LoginScreen(navController = navController) }
                composable(Screen.Feeds.route) { FeedsScreen() }
            }
        }
    }
}
