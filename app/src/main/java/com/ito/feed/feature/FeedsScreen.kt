package com.ito.feed.feature

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ito.feed.ui.theme.FeedTheme
import com.ito.feed.utils.ui.QueryCard

@Composable
fun FeedsScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(state.feeds) { feed ->
            QueryCard(
                url = feed.url,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedsScreen() {
    FeedTheme {
        FeedsScreen()
    }
}
