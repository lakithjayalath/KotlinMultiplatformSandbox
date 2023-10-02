package com.example.kotlinmultiplatformsandbox.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinmultiplatformsandbox.Greeting
import androidx.compose.runtime.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "SpaceX Launch", color = Color.White) },
                            backgroundColor = Color.DarkGray
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            onClick = {
                                showBottomSheet = !showBottomSheet
                            },
                            backgroundColor = Color.Black,
                            text = {
                                Text(
                                    text = "More info",
                                    color = Color.White
                                )
                            },
                        )
                    },
                    bottomBar = {
                        BottomAppBar (
                            backgroundColor = Color.DarkGray,
                            contentColor = Color.White,
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Welcome!!",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                ) { padding ->
                    var text by remember { mutableStateOf("Loading") }
                    var array by remember { mutableStateOf(arrayOf("Loading")) }
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Magenta
                    ) {
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
                                .fillMaxSize(1.0f) // it will fill parent box
                                .padding(8.dp),
                        ) {
                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                ElevatedCardExample(text, array)
                                Spacer(modifier = Modifier.height(10.dp))
                                Button(
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Yellow),
                                    shape = CircleShape,
                                    modifier = Modifier.size(84.dp, 84.dp)) {
                                    Text(
                                        text = "Go to Profile",
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                        if (showBottomSheet) {
                            ModalBottomSheet(
                                onDismissRequest = {
                                    showBottomSheet = false
                                },
                                sheetState = sheetState,
                            ) {
                                // sheet content
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.spacex),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    Button(
                                        onClick = {
                                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                                if (!sheetState.isVisible) {
                                                    showBottomSheet = false
                                                }
                                            }
                                        }
                                    ) {
                                        Text(
                                            text = "Hide bottom sheet"
                                        )
                                    }
                                }
                            }
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
    var expanded by remember { mutableStateOf(false) }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.primaryVariant,
        )
    ) {
        Column(
            Modifier.clickable { expanded = !expanded }
        ) {
            Text(
                text = "Mission : $text",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.h6
            )
            AnimatedVisibility(expanded) {
                GreetingListView(array = array)
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
