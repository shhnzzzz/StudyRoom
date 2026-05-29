package com.example.jetpackcompose.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ── Theme colors (ensure these are accessible) ───────────────


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingsPage(
    onBackClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onDataClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBg) // Set the faint lavender background
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        // 1. Top Bar (Using your reusable component)
        SettingsTopBar (title = "Settings", onBack = onBackClick)

        // 2. Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // PROFILE SECTION
            item { SectionLabel("Profile") }
            item {
                SettingsCard {
                    NavRow(
                        icon = Icons.Default.Person,
                        label = "Account",
                        subtitle = "Email, username, bio",
                        onClick = onAccountClick
                    )
                    CardDivider()
                    NavRow(
                        icon = Icons.Default.Lock,
                        label = "Privacy & Security",
                        subtitle = "Last seen, devices, passkeys",
                        tint = LavenderFaint,
                        onClick = onPrivacyClick
                    )
                }
            }

            // PREFERENCES SECTION
            item { SectionLabel("Preferences") }
            item {
                SettingsCard {
                    NavRow(
                        icon = Icons.AutoMirrored.Filled.Chat,
                        label = "Chat Settings",
                        subtitle = "Wallpaper, night mode",
                        tint = Lavender,
                        onClick = onChatClick
                    )
                    CardDivider()
                    NavRow(
                        icon = Icons.Default.Notifications,
                        label = "Notifications",
                        subtitle = "Sounds, calls, badges",
                        tint = LavenderDeep,
                        onClick = onNotificationsClick
                    )
                    CardDivider()
                    NavRow(
                        icon = Icons.Default.Storage,
                        label = "Data & Storage",
                        subtitle = "Media download settings",
                        tint = Lavender,
                        onClick = onDataClick
                    )
                }
            }

            // SUPPORT SECTION
            item { SectionLabel("Support") }
            item {
                SettingsCard {
                    NavRow(
                        icon = Icons.Default.HelpOutline,
                        label = "Help & FAQ",
                        subtitle = "Get help, report a problem",
                        tint = Lavender

                    )
                    CardDivider()
                    NavRow(
                        icon = Icons.Default.Info,
                        label = "About",
                        subtitle = "App version, licenses",
                        tint = Lavender


                    )
                }
            }

            // Extra space at the bottom
            item { Spacer(Modifier.height(32.dp)) }
        }
    }
}