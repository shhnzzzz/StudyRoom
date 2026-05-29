package com.example.jetpackcompose.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─────────────────────────────────────────────────────────────
//  DATA & STORAGE
// ─────────────────────────────────────────────────────────────
@Composable
fun DataStoragePage(onBack: () -> Unit = {}) {

    var autoDownloadWifi     by remember { mutableStateOf(true)  }
    var autoDownloadMobile   by remember { mutableStateOf(false) }
    var autoDownloadRoaming  by remember { mutableStateOf(false) }
    var reducedDataUsage     by remember { mutableStateOf(false) }

    // Fake usage values for the storage bar
    val totalMb   = 500f
    val usedMb    = 312f
    val usedFrac  = usedMb / totalMb

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        SettingsTopBar(title = "Data & Storage", onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PageBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 40.dp)
        ) {

            // ── Storage usage card ────────────────────────────
            SectionLabel("Storage Usage")
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(16.dp),
                color    = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // Used / Total line
                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Text(
                            text       = "Used: ${usedMb.toInt()} MB",
                            fontSize   = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color      = LavenderDeep
                        )
                        Text(
                            text     = "Total: ${totalMb.toInt()} MB",
                            fontSize = 13.sp,
                            color    = Color.Gray
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    // Progress bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(Color(0xFFEEEEEE), RoundedCornerShape(50))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(usedFrac)
                                .height(8.dp)
                                .background(Lavender, RoundedCornerShape(50))
                        )
                    }

                    Spacer(Modifier.height(14.dp))

                    // Breakdown dots
                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StorageLegendItem(color = Lavender,           label = "Images",    mb = "180 MB")
                        StorageLegendItem(color = Color(0xFF9C6FD6),  label = "Documents", mb = "98 MB")
                        StorageLegendItem(color = Color(0xFFAB82E8),  label = "Other",     mb = "34 MB")
                    }

                    Spacer(Modifier.height(14.dp))

                    // Clear cache button
                    OutlinedButton(
                        onClick  = { /* TODO */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape  = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Lavender)
                    ) {
                        Icon(
                            Icons.Default.DeleteSweep,
                            contentDescription = null,
                            tint               = LavenderDeep,
                            modifier           = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Clear cache", color = LavenderDeep, fontWeight = FontWeight.Medium)
                    }
                }
            }

            // ── Auto-download ─────────────────────────────────
            SectionLabel("Auto-Download Media")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.Wifi,
                    label    = "When on Wi-Fi",
                    subtitle = "Automatically download media",
                    checked  = autoDownloadWifi,
                    onToggle = { autoDownloadWifi = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.NetworkCell,
                    label    = "When on mobile data",
                    subtitle = "May use data allowance",
                    checked  = autoDownloadMobile,
                    onToggle = { autoDownloadMobile = it }
                )
                CardDivider()
                ToggleRow(
                    icon     = Icons.Default.SignalCellularOff,
                    label    = "When roaming",
                    subtitle = "Extra charges may apply",
                    checked  = autoDownloadRoaming,
                    onToggle = { autoDownloadRoaming = it }
                )
            }

            // ── Data Usage ────────────────────────────────────
            SectionLabel("Data Usage")
            SettingsCard {
                ToggleRow(
                    icon     = Icons.Default.DataSaverOn,
                    label    = "Reduced data usage",
                    subtitle = "Lower quality media to save data",
                    checked  = reducedDataUsage,
                    onToggle = { reducedDataUsage = it }
                )
                CardDivider()
                NavRow(
                    icon     = Icons.Default.BarChart,
                    label    = "Network usage",
                    subtitle = "See how much data the app has used"
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

// Small coloured dot + label used in the storage breakdown
@Composable
private fun StorageLegendItem(color: Color, label: String, mb: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color, CircleShape)
        )
        Spacer(Modifier.width(5.dp))
        Column {
            Text(label, fontSize = 11.sp, color = Color.Gray)
            Text(mb,    fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DataStoragePagePreview() {
    DataStoragePage(onBack = {})
}