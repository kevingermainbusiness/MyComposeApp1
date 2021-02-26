package com.kevincodes.mycomposeapp1

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevincodes.mycomposeapp1.ui.theme.MyComposeApp1Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApp1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NewsStory()
                }
            }
        }
    }
}

data class Artist(val name: String, val lastSeenOnline: String)

data class Message(val profilePicture: Int, val userName: String, val lastSeenOnline: String)

@Composable
fun ArtistCard(artist: Artist, onClick: () -> Unit) {
    val padding = 16.dp
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.header),
                contentDescription = null,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .clip(shape = RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop

            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(artist.name)
                Text(artist.lastSeenOnline)
            }
        }
    }
}

@Composable
fun AlignInRow() {
    Row(
        modifier = Modifier
            .size(150.dp)
            .background(Color.Yellow),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Red)
        )
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
fun NewsStory() {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text("Davenport, California", style = typography.body2)
        Text("December 2018", style = typography.body2)
    }
}

@Composable
fun MessageRow(message: Message) {
    val padding = 16.dp
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(message.profilePicture),
                contentDescription = null,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .clip(shape = RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop

            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(message.userName)
                Text(message.lastSeenOnline)
            }
        }
    }
}

@Composable
fun MessageList(messages: List<Message>) {
    Column {
        messages.forEach { message ->
            MessageRow(message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeApp1Theme(darkTheme = false) {
        NewsStory()
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    MyComposeApp1Theme {
        val artist = Artist("Alfred Sisley", "3 minutes ago")
        ArtistCard(artist = artist) {}
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdPreview() {
    AlignInRow()
}

@Preview(showBackground = false)
@Composable
fun MessageListPreview() {
    MyComposeApp1Theme(darkTheme = false) {
        val messages = listOf(
            Message(R.drawable.header, "Jack Dorsey", "4 minutes ago"),
            Message(R.drawable.header, "Jack Doe", "7 minutes ago"),
            Message(R.drawable.header, "John Kia", "1 minutes ago"),
            Message(R.drawable.header, "Jack Matter", "5 minutes ago"),
            Message(R.drawable.header, "Jack Dorsey", "3 minutes ago"),
            Message(R.drawable.header, "Jason Kasino", "2 minutes ago"),
            Message(R.drawable.header, "Keber Quebec", "1 minutes ago")
        )
        MessageList(messages = messages)
    }
}

