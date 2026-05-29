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

// NO local function definitions here — everything comes from settingscomponent.kt
// NO private val colours here — everything comes from appcolor.kt

@Composable
fun PrivacySecurityPage(onBack: () -> Unit = {}) {

    var lastSeen     by remember { mutableStateOf(true)  }
    var readReceipts by remember { mutableStateOf(true)  }
    var twoFactor    by remember { mutableStateOf(false) }
    var profilePhoto by remember { mutableStateOf(true)  }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        SettingsTopBar(title = "Privacy & Security", onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PageBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 40.dp)
        ) {
            SectionLabel("Visibility")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.Visibility,
                    label    = "Last seen",
                    subtitle = "Show when you were last active",
                    checked  = lastSeen,
                    onToggle = { lastSeen = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.DoneAll,
                    label    = "Read receipts",
                    subtitle = "Show when you've read messages",
                    checked  = readReceipts,
                    onToggle = { readReceipts = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.AccountCircle,
                    label    = "Profile photo",
                    subtitle = "Let others see your profile picture",
                    checked  = profilePhoto,
                    onToggle = { profilePhoto = it }
                )
            }

            SectionLabel("Account Security")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.Shield,
                    label    = "Two-factor authentication",
                    subtitle = "Add an extra layer of security",
                    checked  = twoFactor,
                    onToggle = { twoFactor = it }
                )
                CardDivider()
                NavRow(
                    icon     = Icons.Default.Devices,
                    label    = "Active devices",
                    subtitle = "See where you're logged in"
                )
                CardDivider()
                NavRow(
                    icon     = Icons.Default.Key,
                    label    = "Passkeys",
                    subtitle = "Manage biometric login"
                )
            }

            SectionLabel("Data")
            SettingsCard {
                NavRow(
                    icon     = Icons.Default.Block,
                    label    = "Blocked users",
                    subtitle = "Manage who you've blocked",
                    tint     = Color(0xFFE57373)
                )
                CardDivider()
                NavRow(
                    icon     = Icons.Default.DeleteForever,
                    label    = "Delete account",
                    subtitle = "Permanently remove your data",
                    tint     = Color(0xFFE57373)
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrivacySecurityPagePreview() {
    PrivacySecurityPage(onBack = {})
}