package com.example.jetpackcompose.jetpackcompose.chat

enum class MessageTag { NONE, DOUBT, RESOURCE, EXPLANATION }
data class MessageModel(
    val text: String,        // The actual words type
    val isUser: Boolean,    // True if YOU sent it (pink bubble), False if they sent it (gray bubble)
    val tag: MessageTag = MessageTag.NONE, // The study tag (default is no tag)
    val time: String = "12:00 PM",
    var isSelected: Boolean= false
)