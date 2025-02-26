package com.ito.feed.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ito.feed.R
import com.ito.feed.ui.theme.FeedTheme

@Composable
fun LoginContent(
    state: LoginState,
    onUser: (String) -> Unit,
    onToken: (String) -> Unit,
    onLogin: () -> Unit,
    onSkip: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_github_icon),
            contentDescription = null,
            modifier = Modifier
                .size(136.dp)
                .padding(16.dp)
        )

        OutlinedTextField(
            value = state.currentUser,
            onValueChange = { onUser(it) },
            label = { Text(text = stringResource(id = R.string.login_label_username)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        OutlinedTextField(
            value = state.token,
            onValueChange = { onToken(it) },
            label = { Text(text = stringResource(id = R.string.login_label_token)) },
            placeholder = { Text(text = stringResource(id = R.string.login_placeholder_token_text)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Button(
                onClick = { onLogin() },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.7f)
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.login_button_login_text))
            }

            Spacer(modifier = Modifier.height(4.dp))

            TextButton(
                onClick = { onSkip() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.login_button_skip_text))
            }
        }

        TextButton(
            onClick = {
                throw RuntimeException("Test Crash") // Test crash :)
            }
        ) {
            Text(text = stringResource(id = R.string.login_button_crash_text))
        }
    }
}

@Preview
@Composable
fun PreviewLoginContent() {
    FeedTheme {
        LoginContent(
            state = LoginState(),
            onUser = { },
            onToken = { },
            onLogin = { },
            onSkip = { },
        )
    }
}
