package com.ito.feed.feature.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ito.feed.R
import com.ito.feed.utils.model.FeedsModel
import com.ito.feed.utils.ui.QueryCard

@Composable
fun FeedsContent(
    feeds: List<FeedsModel>,
    parameterInputs: List<String>,
    onFieldSelected: (Int) -> Unit,
    onFieldValueChange: (String) -> Unit,
    onGetClicked: (Int) -> Unit,
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

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            feeds.forEach { feed ->
                QueryCard(
                    path = feed.path,
                    pathParameters = feed.parameters,
                    parameterInputs = parameterInputs,
                    cardId = feed.cardId,
                    onFieldSelected = onFieldSelected,
                    onFieldValueChange = onFieldValueChange,
                    onGetClicked = onGetClicked
                )
            }
        }
    }
}
