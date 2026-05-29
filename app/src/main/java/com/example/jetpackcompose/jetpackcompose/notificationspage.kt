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
//  NOTIFICATIONS
// ─────────────────────────────────────────────────────────────
@Composable
fun NotificationsPage(onBack: () -> Unit = {}) {

    var msgNotifs    by remember { mutableStateOf(true)  }
    var groupNotifs  by remember { mutableStateOf(true)  }
    var joinNotifs   by remember { mutableStateOf(true)  }
    var mentionNotifs by remember { mutableStateOf(true) }
    var sound        by remember { mutableStateOf(true)  }
    var vibration    by remember { mutableStateOf(false) }
    var badge        by remember { mutableStateOf(true)  }
    var previewText  by remember { mutableStateOf(true)  }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        SettingsTopBar(title = "Notifications", onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PageBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 40.dp)
        ) {

            // ── Notify me about ───────────────────────────────
            SectionLabel("Notify me about")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.Chat,
                    label    = "Direct messages",
                    subtitle = "Notifications for new DMs",
                    checked  = msgNotifs,
                    onToggle = { msgNotifs = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.Group,
                    label    = "Group messages",
                    subtitle = "Notifications for group activity",
                    checked  = groupNotifs,
                    onToggle = { groupNotifs = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.PersonAdd,
                    label    = "Join requests",
                    subtitle = "When someone requests to join your group",
                    checked  = joinNotifs,
                    onToggle = { joinNotifs = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.AlternateEmail,
                    label    = "Mentions",
                    subtitle = "When someone @mentions you",
                    checked  = mentionNotifs,
                    onToggle = { mentionNotifs = it }
                )
            }

            // ── Alert style ───────────────────────────────────
            SectionLabel("Alert Style")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.VolumeUp,
                    label    = "Sound",
                    subtitle = "Play a sound for notifications",
                    checked  = sound,
                    onToggle = { sound = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.Vibration,
                    label    = "Vibration",
                    subtitle = "Vibrate on new notifications",
                    checked  = vibration,
                    onToggle = { vibration = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.NotificationsActive,
                    label    = "Badge count",
                    subtitle = "Show unread count on app icon",
                    checked  = badge,
                    onToggle = { badge = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.Preview,
                    label    = "Show preview",
                    subtitle = "Display message text in notification",
                    checked  = previewText,
                    onToggle = { previewText = it }
                )
            }

            // ── Ringtone ──────────────────────────────────────
            SectionLabel("Ringtone")
            SettingsCard {
                ValueRow(
                    icon  = Icons.Default.MusicNote,
                    label = "Message tone",
                    value = "Default"
                )
                CardDivider()
                ValueRow(
                    icon  = Icons.Default.Notifications,
                    label = "Group tone",
                    value = "Default"
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NotificationsPagePreview() {
    NotificationsPage(onBack = {})
}