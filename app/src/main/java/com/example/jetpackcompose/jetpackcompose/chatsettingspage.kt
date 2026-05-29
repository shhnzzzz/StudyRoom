package com.example.jetpackcompose.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ─────────────────────────────────────────────────────────────
//  CHAT SETTINGS
// ─────────────────────────────────────────────────────────────
@Composable
fun ChatSettingsPage(onBack: () -> Unit = {}) {

    var nightMode    by remember { mutableStateOf(false) }
    var animations   by remember { mutableStateOf(true)  }
    var enterToSend  by remember { mutableStateOf(false) }
    var mediaPreview by remember { mutableStateOf(true)  }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        SettingsTopBar(title = "Chat Settings", onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PageBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 40.dp)
        ) {

            // ── Appearance ────────────────────────────────────
            SectionLabel("Appearance")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.DarkMode,
                    label    = "Night mode",
                    subtitle = "Switch to a dark chat background",
                    checked  = nightMode,
                    onToggle = { nightMode = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.Animation,
                    label    = "Animations",
                    subtitle = "Enable message send animations",
                    checked  = animations,
                    onToggle = { animations = it }
                )
                CardDivider()
                NavRow(
                    icon     = Icons.Default.Wallpaper,
                    label    = "Chat wallpaper",
                    subtitle = "Customise your chat background"
                )
                CardDivider()
                ValueRow(
                    icon    = Icons.Default.TextFields,
                    label   = "Font size",
                    value   = "Medium"
                )
            }

            // ── Behaviour ─────────────────────────────────────
            SectionLabel("Behaviour")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.Send,
                    label    = "Enter to send",
                    subtitle = "Press Enter key to send messages",
                    checked  = enterToSend,
                    onToggle = { enterToSend = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.Image,
                    label    = "Media preview",
                    subtitle = "Show image previews in chat",
                    checked  = mediaPreview,
                    onToggle = { mediaPreview = it }
                )
            }

            // ── History ───────────────────────────────────────
            SectionLabel("History")
            SettingsCard {
                NavRow(
                    icon     = Icons.Default.FileDownload,
                    label    = "Export chat history",
                    subtitle = "Save your messages as a file"
                )
                CardDivider()
                NavRow(
                    icon     = Icons.Default.DeleteSweep,
                    label    = "Clear all chats",
                    subtitle = "Delete all messages locally",
                    tint     = Color(0xFFE57373)
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChatSettingsPagePreview() {
    ChatSettingsPage(onBack = {})
}
