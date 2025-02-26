package com.ito.feed.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ito.feed.feature.navigation.Screen
import com.ito.feed.ui.theme.FeedTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    LoginContent(
        state = state,
        onUser = viewModel::updateUser,
        onToken = viewModel::updateToken,
        onLogin = {
            navigateToFeedsScreen(navController)
            viewModel.onLogin()
        },
        onSkip = {
            navigateToFeedsScreen(navController)
        }
    )
}

private fun navigateToFeedsScreen(navController: NavController) {
    navController.navigate(Screen.Feeds.route)
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    FeedTheme {
        LoginScreen(navController = navController)
    }
}
