package com.example.kotlinmultiplatformsandbox.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinmultiplatformsandbox.Greeting
import androidx.compose.runtime.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Magenta,
                ) {
                    var text by remember { mutableStateOf("Loading") }
                    var array by remember { mutableStateOf(arrayOf("Loading")) }
                    LaunchedEffect(true) {
                        array = try {
                            Greeting().greetMessage()
                        } catch (e: Exception) {
                            arrayOf(e.localizedMessage ?: "error")
                        }

                        text = try {
                            Greeting().getMissionName()
                        } catch (e: Exception) {
                            e.localizedMessage ?: "error"
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.spacex),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter) // Align the image to the top center
                                .padding(start = 8.dp, top = 32.dp, end = 8.dp)
                        )
                    }
                    Box(modifier = Modifier
                        .fillMaxSize(1.0f) // it will fill parent box
                        .padding(8.dp),// padding will help us to give some margin between our text and parent if text greater then our parent size
                        contentAlignment = Alignment.Center) { // contentAlignment will align its content as provided Alignment in our case it's Center
                        Column {
                            ElevatedCardExample(text, array)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Composable
fun GreetingListView(array: Array<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        array.forEach { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(start = 16.dp),
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun ElevatedCardExample(text: String, array: Array<String>) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 200.dp)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.primaryVariant,
        )
    ) {
        Text(
            text = "Mission : $text",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.h6
        )
        GreetingListView(array = array)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
