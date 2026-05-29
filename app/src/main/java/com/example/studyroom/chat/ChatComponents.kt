package com.example.studyroom.chat

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// --- THE MESSAGE BUBBLE ---
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatBubble(
    message: MessageModel,
    isSelected: Boolean,
    onLongClick: () -> Unit,
    onClick: () -> Unit
) {
    val themePink = Color(0xFFE6B3D1)
    val isMedia = message.text.startsWith("IMAGE_URI:") ||
            message.text.startsWith("VIDEO_URI:")

    val selectionOverlay = if (isSelected) Color.Black.copy(alpha = 0.1f) else Color.Transparent
    val bubbleColor = if (message.isUser) Color(0xFFE6B3D1) else Color(0xFFF3F3F3)
    val textColor = if (message.isUser) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if(isSelected) themePink.copy(alpha=0.2f) else Color.Transparent)

            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            if(isSelected){
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = themePink,
                    modifier = Modifier.padding(end = 8.dp).size(24.dp)
                )
            }

            Surface(
                color = bubbleColor,
                shape = RoundedCornerShape(16.dp),
                shadowElevation = if (isSelected) 4.dp else 0.dp
            ) {
                Column(modifier = Modifier.padding(if (isMedia) 4.dp else 12.dp)) {
                    if (message.tag != MessageTag.NONE) {
                        Text(
                            text = message.tag.name,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            color = if (message.isUser) Color.White.copy(0.7f) else Color(0xFFE6B3D1),
                            modifier = Modifier.padding(horizontal = if (isMedia) 4.dp else 0.dp)
                        )
                    }

                    when {
                        message.text.startsWith("IMAGE_URI:") -> {
                            val imageUri = message.text.removePrefix("IMAGE_URI:")
                            AsyncImage(
                                model = imageUri,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(top = if (message.tag != MessageTag.NONE)4.dp else 0.dp)
                                    .sizeIn(maxWidth = 250.dp, maxHeight = 300.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                        }

                        message.text.startsWith("VIDEO_URI:") -> {
                            Box(
                                modifier = Modifier
                                    .padding(top = if (message.tag== MessageTag.NONE)4.dp else 0.dp)
                                    .size(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Black),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.PlayCircle, null, tint = Color.White, modifier = Modifier.size(50.dp))
                                Text("Video", color = Color.White.copy(0.7f), modifier = Modifier.align(Alignment.BottomCenter).padding(8.dp))
                            }
                        }

                        message.text.startsWith("FILE_URI:") -> {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .background(Color.Black.copy(0.05f), RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Icon(Icons.Default.Description, null, tint = textColor)
                                Spacer(Modifier.width(8.dp))
                                Text("Document.pdf", color = textColor, fontSize = 14.sp)
                            }
                        }

                        message.text.startsWith("http") || message.text.contains("www.") -> {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(0.05f)),
                                modifier = Modifier.padding(top = 4.dp).width(200.dp)
                            ) {
                                Column(Modifier.padding(8.dp)) {
                                    Icon(Icons.Default.Link, null, tint = Color.Blue,modifier = Modifier.size(20.dp))
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = message.text,
                                        color = Color.Blue,
                                        textDecoration = TextDecoration.Underline,
                                        fontSize = 13.sp,
                                        maxLines = 2
                                       // overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }

                        else -> {
                            Text(text = message.text, color = textColor)
                        }
                    }
                }
            }
        }
    }
}

// --- THE INPUT AREA ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(onSend: (String, MessageTag?) -> Unit) {
    var text by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf(MessageTag.NONE) }
    var showMediaSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            // Persist permission so the image stays visible after app restart
            try {
                context.contentResolver.takePersistableUriPermission(
                    it, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: Exception) {}

            val type = context.contentResolver.getType(it)
            if (type?.startsWith("video") == true) {
                onSend("VIDEO_URI:$it", MessageTag.NONE)
            } else {
                onSend("IMAGE_URI:$it", MessageTag.NONE)
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) onSend("IMAGE: Captured from Camera", MessageTag.NONE)
    }

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) onSend("FILE_URI:$uri", MessageTag.NONE)
    }

    if (showMediaSheet) {
        MediaPickerSheet(
            onDismiss = { showMediaSheet = false },
            onOptionSelected = { type ->
                showMediaSheet = false
                when (type) {
                    "IMAGE" -> galleryLauncher.launch("*/*")
                    "CAMERA" -> cameraLauncher.launch(null)
                    "FILE" -> fileLauncher.launch("application/pdf")
                }
            }
        )
    }

    Column(Modifier.background(Color.White)) {
        Row(Modifier.padding(horizontal = 8.dp)) {
            MessageTag.entries.filter { it != MessageTag.NONE }.forEach { tag ->
                FilterChip(
                    selected = selectedTag == tag,
                    onClick = { selectedTag = if (selectedTag == tag) MessageTag.NONE else tag },
                    label = { Text(tag.name.lowercase(), fontSize = 10.sp) },
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }

        Row(Modifier.padding(8.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                keyboard?.hide()
                showMediaSheet = true
            }) {
                Icon(Icons.Default.Add, null, tint = Color.Gray)
            }

            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message...") },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            IconButton(onClick = {
                if (text.isNotBlank()) {
                    onSend(text, selectedTag)
                    text = ""
                    selectedTag = MessageTag.NONE
                    keyboard?.hide()
                }
            }) {
                Icon(Icons.Default.Send, null, tint = Color(0xFFE6B3D1))
            }
        }
    }
}