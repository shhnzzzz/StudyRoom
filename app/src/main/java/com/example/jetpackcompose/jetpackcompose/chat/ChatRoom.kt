package com.example.jetpackcompose.jetpackcompose.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.net.Uri

@Composable
fun ChatRoom() {
    val messages = remember {
        mutableStateListOf(
            MessageModel("Did you get the notes?", false, MessageTag.RESOURCE),
            MessageModel("Yes, checking them now.", true)
        )
    }

    // --- STATE VARIABLES ---
    var selectedMediaUri by remember { mutableStateOf<String?>(null) }
    var selectedMediaType by remember { mutableStateOf("") }
    var context= LocalContext.current
    val selectedMessages = remember { mutableStateListOf<MessageModel>() }
    val isInSelectionMode by remember { derivedStateOf { selectedMessages.isNotEmpty() } }

    var isChatActive by remember { mutableStateOf(true) }
    var isChatDeleted by remember { mutableStateOf(false) }
    var isViewingProfile by remember { mutableStateOf(false) }
    var isViewingMedia by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }
    var isBlocked by remember { mutableStateOf(false) }
    var showReportDialog by remember { mutableStateOf(false) }

    // --- DIALOGS ---
    if (showReportDialog) {
        AlertDialog(
            onDismissRequest = { showReportDialog = false },
            title = { Text("Report User") },
            text = { Text("User reported. Would you like to block this user as well?") },
            confirmButton = {
                TextButton(onClick = {
                    isBlocked = true
                    showReportDialog = false
                }) { Text("Block", color = Color.Red) }
            },
            dismissButton = {
                TextButton(onClick = { showReportDialog = false }) { Text("Cancel") }
            }
        )
    }

    // --- BACK NAVIGATION LOGIC (FIXED) ---
    BackHandler(enabled = selectedMediaUri != null || isViewingProfile || isChatDeleted || isViewingMedia || isInSelectionMode || !isChatActive) {
        if (selectedMediaUri != null) {
            selectedMediaUri = null
           selectedMediaType=""
        } else if (isInSelectionMode) {
            selectedMessages.clear()
        } else if (isViewingProfile) {
            isViewingProfile = false
        } else if (isViewingMedia) {
            isViewingMedia = false
        } else if (isChatDeleted) {
            isChatDeleted = false
        } else if (!isChatActive) {
            isChatActive = true
        }
    }

    // --- SCREEN NAVIGATION ---
    when {
        selectedMediaUri != null -> {
            MediaViewerScreen(
                uri = selectedMediaUri!!,
                type=selectedMediaType,
                onBack = {
                    selectedMediaUri = null
                    selectedMediaType=""
                }
            )
        }

        isViewingProfile -> {
            UserProfileScreen(
                name = "Astha",
                isMuted = isMuted,
                isBlocked = isBlocked,
                onBack = { isViewingProfile = false },
                onBlockClick = { isBlocked = !isBlocked },
                onMessageClick = { isViewingProfile = false },
                onReportClick = { showReportDialog = true },
                onMuteClick = { muted -> isMuted = muted }
            )
        }

        !isChatActive -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Chat List Screen Placeholder")
                    Button(onClick = { isChatActive = true }) { Text("Re-enter Chat") }
                }
            }
        }

        isViewingMedia -> {
            SharedMediaScreen(
                allMessages = messages,
                onBack = { isViewingMedia = false }
            )
        }

        isChatDeleted -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("This chat has been deleted")
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { isChatDeleted = false }) { Text("Go Back") }
                }
            }
        }

        else -> {
            Scaffold(
                contentWindowInsets = WindowInsets.statusBars,
                topBar = {
                    if (isInSelectionMode) {
                        SelectionTopBar(
                            selectedCount = selectedMessages.size,
                            onClose = { selectedMessages.clear() },
                            onDelete = {
                                messages.removeAll(selectedMessages)
                                selectedMessages.clear()
                            }
                        )
                    } else {
                        TopBar(
                            userName = "Astha",
                            onBack = { isChatActive = false },
                            isBlocked = isBlocked,
                            onViewUser = { isViewingProfile = true },
                            onSharedMedia = { isViewingMedia = true },
                            onClearChat = { messages.clear() },
                            onDeleteChat = {
                                messages.clear()
                                isChatDeleted = true
                            },
                            onBlock = { isBlocked = !isBlocked },
                            onReport = { showReportDialog = true }
                        )
                    }
                },
                bottomBar = {
                    if (!isBlocked) {
                        MessageInput(onSend = { typedText, selectedTag ->
                            messages.add(MessageModel(typedText, true, selectedTag ?: MessageTag.NONE))
                        })
                    } else {
                        BottomAppBar {
                            Text(
                                "You have blocked this user",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier.padding(padding).fillMaxSize()
                ) {
                    items(messages) { msg ->
                        ChatBubble(
                            message = msg,
                            isSelected = selectedMessages.contains(msg),
                            onLongClick = {
                                if (!isInSelectionMode) selectedMessages.add(msg)
                            },
                            onClick = {
                                if (isInSelectionMode) {
                                    if (selectedMessages.contains(msg)) selectedMessages.remove(msg)
                                    else selectedMessages.add(msg)
                                } else {
                                    when {
                                        msg.text.startsWith("IMAGE_URI:") -> {
                                            selectedMediaUri = msg.text.removePrefix("IMAGE_URI:")
                                            selectedMediaType = "IMAGE"
                                        }

                                        msg.text.startsWith("VIDEO_URI:") -> {
                                            selectedMediaUri = msg.text.removePrefix("VIDEO_URI:")
                                            selectedMediaType = "VIDEO"
                                        }

                                        msg.text.startsWith("FILE_URI:") -> {
                                            selectedMediaUri = msg.text.removePrefix("FILE_URI:")
                                            selectedMediaType = "PDF"
                                        }

                                        msg.text.startsWith("http") || msg.text.contains("www.") -> {
                                            val url =
                                                if (!msg.text.startsWith("http")) "https://${msg.text}" else msg.text
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                            context.startActivity(intent)
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}