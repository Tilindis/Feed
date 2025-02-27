package com.ito.feed.utils.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ito.feed.R
import com.ito.feed.ui.theme.FeedTheme
import com.ito.feed.utils.domain.Parameter

@Composable
fun QueryCard(
    path: String,
    pathParameters: List<Parameter>,
    parameterInputs: List<String>,
    cardId: Int,
    onFieldSelected: (Int) -> Unit,
    onFieldValueChange: (String) -> Unit,
    onGetClicked: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.medium)
            .background(color = Color.Gray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal =  8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_get),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )

                Text(
                    text = path,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }

            pathParameters.forEach { parameter ->
                OutlinedTextField(
                    value = parameterInputs[parameter.fieldNumber],
                    onValueChange = { onFieldValueChange(it) },
                    label = { Text(text = parameter.name) },
                    placeholder = { Text(text = parameter.name) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                onFieldSelected(parameter.fieldNumber)
                            }
                        }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onGetClicked(cardId) },
                    modifier = Modifier
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = stringResource(id = R.string.feed_card_button_get_text))
                }
            }
        }
    }
}

@Composable
@Preview(name = "Card Normal", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Card Normal", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun Preview(modifier: Modifier = Modifier) {
    FeedTheme {
        QueryCard(
            path = "https://github.com/{user}",
            pathParameters = listOf(Parameter("user", 0,0)),
            parameterInputs = listOf(),
            onFieldSelected = { },
            onFieldValueChange = { },
            onGetClicked = { },
            cardId = 0
        )
    }
}
