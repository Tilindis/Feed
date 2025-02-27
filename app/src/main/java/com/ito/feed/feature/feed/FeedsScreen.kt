package com.ito.feed.feature.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ito.feed.ui.theme.FeedTheme

@Composable
fun FeedsScreen(
    viewModel: FeedsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value
    FeedsContent(
        feeds = state.feeds,
        parameterInputs = state.parameterInputs,
        onFieldSelected = viewModel::setSelectedInputField,
        onFieldValueChange = viewModel::setSelectedInputValue,
        onGetClicked = viewModel::requestFeed
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedsScreen() {
    FeedTheme {
        FeedsScreen()
    }
}
