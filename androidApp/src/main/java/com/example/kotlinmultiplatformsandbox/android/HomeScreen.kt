package com.example.kotlinmultiplatformsandbox.android

import androidx.compose.foundation.Image
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
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kotlinmultiplatformsandbox.Greeting
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
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
                        onClick = {
                               navController.navigate(route = Screen.Detail.route)
                        },
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Yellow),
                        shape = CircleShape,
                        modifier = Modifier.size(84.dp, 84.dp)) {
                        Text(
                            text = "Go to Detail",
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