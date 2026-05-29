package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.jetpackcompose.ChatSettingsPage
import com.example.jetpackcompose.jetpackcompose.DataStoragePage
import com.example.jetpackcompose.jetpackcompose.EditProfile
import com.example.jetpackcompose.jetpackcompose.NotificationsPage
import com.example.jetpackcompose.jetpackcompose.PrivacySecurityPage
import com.example.jetpackcompose.jetpackcompose.ProfilePage
import com.example.jetpackcompose.jetpackcompose.SettingsPage
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController    = navController,
                    startDestination = "profile"
                ) {

                    // ── Profile ───────────────────────────────
                    composable("profile") {
                        ProfilePage(
                            onSettingsClick = { navController.navigate("settings") },
                            onEditClick     = { navController.navigate("edit_profile") }
                        )
                    }

                    // ── Settings ──────────────────────────────
                    composable("settings") {
                        SettingsPage(
                            onBackClick          = { navController.popBackStack() },
                            onAccountClick       = { navController.navigate("edit_profile") },
                            onChatClick          = { navController.navigate("chat_settings") },
                            onPrivacyClick       = { navController.navigate("privacy_security") },
                            onNotificationsClick = { navController.navigate("notifications") },
                            onDataClick          = { navController.navigate("data_storage") }
                        )
                    }

                    // ── Edit Profile ──────────────────────────
                    composable("edit_profile") {
                        EditProfile(
                            onBack   = { navController.popBackStack() },
                            onSave   = { navController.popBackStack() },
                            onCancel = { navController.popBackStack() }
                        )
                    }

                    // ── Chat Settings ─────────────────────────
                    composable("chat_settings") {
                        ChatSettingsPage(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // ── Privacy & Security ────────────────────
                    composable("privacy_security") {
                        PrivacySecurityPage(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // ── Notifications ─────────────────────────
                    composable("notifications") {
                        NotificationsPage(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // ── Data & Storage ────────────────────────
                    composable("data_storage") {
                        DataStoragePage(
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
