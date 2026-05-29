package com.example.jetpackcompose.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Shared theme (same as EditProfile & SettingsPage) ─────────


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePage(
    onSettingsClick: () -> Unit = {},
    onEditClick    : () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        // ── Top bar ── same pattern as EditProfile / Settings ──
        Box(
            modifier         = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = "Profile",
                fontSize   = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color      = Color.Black
            )

            // Edit + Settings on the right
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                TopBarIconBtn(
                    icon               = Icons.Default.Edit,
                    contentDescription = "Edit profile",
                    onClick            = onEditClick
                )
                TopBarIconBtn(
                    icon               = Icons.Default.Settings,
                    contentDescription = "Settings",
                    onClick            = onSettingsClick
                )
            }
        }

        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 0.8.dp)

        // ── Scrollable body ────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PageBg)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
                .padding(top = 24.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ── Avatar ─────────────────────────────────────────
            Box(
                modifier         = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEEEEEE))
                    .border(2.5.dp, Lavender, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = Icons.Default.Person,
                    contentDescription = null,
                    modifier           = Modifier.size(48.dp),
                    tint               = Color.Gray
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text       = "Shahnaz",
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold,
                color      = Color.Black
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text     = "xyz@gmail.com",
                fontSize = 13.sp,
                color    = Color.Gray
            )

            Spacer(Modifier.height(20.dp))

            // ── Stats row (Groups + Friends only) ─────────────
            Surface(
                modifier        = Modifier.fillMaxWidth(),
                shape           = RoundedCornerShape(16.dp),
                color           = Color.White,
                shadowElevation = 0.dp
            ) {
                Row(
                    modifier              = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileStat(value = "12",  label = "Groups")
                    // vertical divider
                    Box(
                        Modifier
                            .width(0.8.dp)
                            .height(40.dp)
                            .background(Color(0xFFEEEEEE))
                            .align(Alignment.CenterVertically)
                    )
                    ProfileStat(value = "45", label = "Friends")
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Bio card ───────────────────────────────────────
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(16.dp),
                color    = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text       = "BIO",
                        fontSize   = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color      = LavenderDeep,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text     = "Computer Science student passionate about Android App development.",
                        fontSize = 14.sp,
                        color    = Color.Black,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Quick action cards ─────────────────────────────


            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(16.dp),
                color    = Color.White
            ) {
                Column {
                    ProfileActionRow(
                        icon    = Icons.Default.Group,
                        label   = "My Groups",
                        subtitle = "See all groups you've joined",
                        tint    = LavenderDeep
                    )
                    HorizontalDivider(
                        modifier  = Modifier.padding(start = 62.dp),
                        color     = Color(0xFFF0EEFF),
                        thickness = 0.8.dp
                    )
                    ProfileActionRow(
                        icon     = Icons.Default.Bookmark,
                        label    = "Saved Messages",
                        subtitle = "Notes and links you saved",
                        tint     = Color(0xFF9C6FD6)
                    )
                    HorizontalDivider(
                        modifier  = Modifier.padding(start = 62.dp),
                        color     = Color(0xFFF0EEFF),
                        thickness = 0.8.dp
                    )
                    ProfileActionRow(
                        icon     = Icons.Default.Tag,
                        label    = "My Interests",
                        subtitle = "Manage your interest tags",
                        tint     = Color(0xFFAB82E8)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── Logout ─────────────────────────────────────────
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(16.dp),
                color    = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: logout */ }
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier         = Modifier
                            .size(40.dp)
                            .background(
                                color = Color.Red.copy(alpha = 0.09f),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            tint               = Color.Red.copy(alpha = 0.8f),
                            modifier           = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.width(14.dp))
                    Text(
                        text       = "Log out",
                        fontSize   = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color      = Color.Red.copy(alpha = 0.8f),
                        modifier   = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

// ── Helper composables ────────────────────────────────────────

@Composable
private fun TopBarIconBtn(
    icon              : ImageVector,
    contentDescription: String,
    onClick           : () -> Unit
) {
    IconButton(onClick = onClick, modifier = Modifier.size(48.dp)) {
        Box(
            modifier         = Modifier
                .size(34.dp)
                .background(LavenderFaint, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector        = icon,
                contentDescription = contentDescription,
                tint               = LavenderDeep,
                modifier           = Modifier.size(17.dp)
            )
        }
    }
}


@Composable
fun ProfileStat(value: String, label: String) {
    Column(
        modifier            = Modifier.padding(vertical = 14.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text       = value,
            fontWeight = FontWeight.Bold,
            fontSize   = 20.sp,
            color      = LavenderDeep
        )
        Spacer(Modifier.height(2.dp))
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
    }
}

@Composable
private fun ProfileActionRow(
    icon    : ImageVector,
    label   : String,
    subtitle: String,
    tint    : Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO */ }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(40.dp)
                .background(tint.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector        = icon,
                contentDescription = null,
                tint               = tint,
                modifier           = Modifier.size(20.dp)
            )
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(label,    fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Icon(
            imageVector        = Icons.Default.ChevronRight,
            contentDescription = null,
            tint               = Color(0xFFCCCCCC),
            modifier           = Modifier.size(20.dp)
        )
    }
}