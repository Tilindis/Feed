package com.ito.feed.utils.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ito.feed.R
import com.ito.feed.ui.theme.FeedTheme

// Test Screen Card
@Composable
fun QueryCard(url: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.medium)
            .background(color = Color.Gray)
            // add clickbale
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Red,
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp)
                .height(20.dp)
            ) {
                Image(modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp) ,
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.height(16.dp),
                    text = url,
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .height(60.dp)
                    .width(120.dp)
                ,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFA5B3D),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = "Send",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(name = "Card Normal", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Card Normal", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun Preview(modifier: Modifier = Modifier) {
    FeedTheme {
        QueryCard(url = "https://github.com/{user}")
    }
}