package com.example.studyroom.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    userName: String,
    onBack: () -> Unit,
    onViewUser: () -> Unit,
    isBlocked: Boolean,
    onSharedMedia: () -> Unit,
    onClearChat: () -> Unit,
    onDeleteChat: () -> Unit,
    onBlock: () -> Unit,
    onReport: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    val themePink = Color(0xFFE6B3D1)

    Surface(color = themePink, modifier = Modifier.fillMaxWidth()) {
        Column {
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            TopAppBar(
                windowInsets = WindowInsets(0, 0, 0, 0),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themePink),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                    }
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onViewUser() }
                    ) {
                        Box(
                            modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(24.dp))
                        }
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(userName, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("Online", color = Color.White.copy(0.8f), fontSize = 11.sp)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, "Menu", tint = Color.White)
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(text = { Text("View User") }, onClick = { onViewUser(); showMenu = false })
                        DropdownMenuItem(text = { Text("Shared Media") }, onClick = { onSharedMedia(); showMenu = false })
                        DropdownMenuItem(text = { Text("Clear History") }, onClick = { onClearChat(); showMenu = false })
                        DropdownMenuItem(text = { Text("Delete Chat", color = Color.Red) }, onClick = { onDeleteChat(); showMenu = false })
                        DropdownMenuItem(text = { Text(if (isBlocked) "Unblock" else "Block", color = Color.Red) }, onClick = { onBlock(); showMenu = false })
                        DropdownMenuItem(text = { Text("Report User", color = Color.Red) }, onClick = { onReport(); showMenu = false })
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionTopBar(selectedCount: Int, onClose: () -> Unit, onDelete: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets.statusBars,
        title = { Text("$selectedCount selected", color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE6B3D1)),
        navigationIcon = {
            IconButton(onClick = onClose) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White) }
        },
        actions = {
            IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White) }
        }
    )
}