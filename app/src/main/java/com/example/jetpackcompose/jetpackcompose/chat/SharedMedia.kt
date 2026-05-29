package com.example.jetpackcompose.jetpackcompose.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedMediaScreen(allMessages: List<MessageModel>, onBack: () -> Unit) {
    val tabs = listOf("Notes", "Resources", "Explanations")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shared Media") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Standard TabRow (Works in all M3 versions)
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }


            val filteredMessages = when (selectedTabIndex) {
                0 -> allMessages.filter { it.tag == MessageTag.DOUBT }
                1 -> allMessages.filter { it.tag == MessageTag.RESOURCE }
                2 -> allMessages.filter { it.tag == MessageTag.EXPLANATION }
                else -> emptyList()
            }

            if (filteredMessages.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No ${tabs[selectedTabIndex]} shared yet", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredMessages) { msg ->
                        MediaItemCard(msg)
                    }
                }
            }
        }
    }
}

@Composable
fun MediaItemCard(message: MessageModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Description,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = message.text,
                    fontSize = 14.sp,
                    maxLines = 2
                )
                // Optional: add a "Sent by me/them" subtext
                Text(
                    text = "${if (message.isUser) "Sent by you" else "Shared by Astha"}.${message.time}",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}