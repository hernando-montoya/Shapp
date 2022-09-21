// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var nextLeadName by remember { mutableStateOf("The next lead is...") }
    val listDevs = listOf(
        Dev("a"),
        Dev("b"),
        Dev("c"),
        Dev("d"),
    )
    var devs by remember { mutableStateOf(listDevs) }

    val listState = rememberLazyListState()

    MaterialTheme {
        Column(
            Modifier.fillMaxSize()
                .padding(8.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(text = "Android Devs", fontSize = 40.sp)
                Button(modifier = Modifier.padding(start = 8.dp), onClick = {
                    devs = listDevs
                }) {
                    Text("Reload List")
                }
            }
            LazyColumn(state = listState) {
                items(devs) { item ->
                    Row(Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = {
                                devs = devs.toMutableList().also { it.remove(item) }
                                nextLeadName = "The next lead is..."
                            }
                        ) {
                            Icon(Icons.Default.Delete, "delete")
                        }
                        Text(
                            text = item.name,
                            Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Spacer(Modifier.padding(16.dp))
            Button(onClick = {
                val nextLead = devs.asSequence().shuffled().find { true }
                nextLeadName = nextLead!!.name
            }) {
                Text(text = "Pick Next Lead")
            }
            Text(text = nextLeadName, fontSize = 40.sp)
        }

    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "The random Shapping Lead"
    ) {
        App()
    }
}
