package com.example.jetpackcompose.jetpackcompose.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//import androidx.compose.material.icons.filled.Report

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    name: String,
    isMuted: Boolean,
    isBlocked:Boolean,
    onBack: () -> Unit,
    onMessageClick:()-> Unit,
    onMuteClick:(Boolean)->Unit,
    onBlockClick:()->Unit,
    onReportClick:()-> Unit,
    ) {
    val interests = listOf("Kotlin", "Jetpack Compose", "UI/UX", "Python", "Data Science")
    var showMoreMenu by remember { mutableStateOf(false) }
    //var isMuted by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick ={onBack()}){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text("Profile") },
                actions = {
                    IconButton(onClick = {showMoreMenu=true}) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = showMoreMenu,
                        onDismissRequest = {showMoreMenu=false}
                    ) {
                        DropdownMenuItem(
                            text={Text("Add to Study Group")},
                            onClick={showMoreMenu=false},
                            leadingIcon = {Icon(Icons.Default.GroupAdd, contentDescription = null)}
                        )
                        DropdownMenuItem(
                            text={Text("Share Profile")},
                            onClick = {showMoreMenu=false},
                            leadingIcon = { Icon(imageVector = Icons.Default.Share, contentDescription = null) }
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = {Text(if(isBlocked)"Unblock User" else "Block User", color=Color.Red)},
                            onClick={
                                onBlockClick()
                                showMoreMenu=false
                            },
                            leadingIcon = {Icon(Icons.Default.Block, contentDescription = null,tint=Color.Red)}
                        )
                        DropdownMenuItem(
                            text={Text("Report User",color=Color.Red)},
                            onClick = {
                                onReportClick()
                                showMoreMenu=false

                            },
                            leadingIcon = {
                                Icon(
                                    imageVector=Icons.Default.Report,
                                    contentDescription = null,
                                    tint=Color.Red
                                )
                            }
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 1. Profile Picture (Circle with Initial)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(name.take(1), fontSize = 40.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 2. Name & Joined Date
            Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Joined Jan 2024", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Telegram Style Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileActionButton(
                    icon=if (isMuted)Icons.Default.NotificationsOff else Icons.Default.Notifications,
                    label=if(isMuted)"Unmuted" else "Mute",
                    onClick = {
                        onMuteClick(!isMuted)
                    }
                )
                ProfileActionButton(icon = Icons.AutoMirrored.Filled.Chat, label = "Message",onClick={onMessageClick()})
              //  ProfileActionButton(icon=Icons.Default.Search,label="Search",onClick={})

               // ProfileActionButton(icon = Icons.Default.MoreHoriz, label = "More", onClick={ /*empty for now*/})
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp), thickness = 0.5.dp)

            // 4. Interests (Using Chips)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Interests", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(interests) { item ->
                        SuggestionChip(
                            onClick = { },
                            label = { Text(item) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Common Groups Section
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Groups in Common", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
                GroupItem(groupName = "Android Devs", members = "12k members")
                GroupItem(groupName = "Study Room X", members = "45 members")
            }
        }
    }
}

@Composable
fun ProfileActionButton(icon: ImageVector, label: String, onClick:()-> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledTonalIconButton(onClick = onClick, modifier = Modifier.size(50.dp)) {
            Icon(icon, contentDescription = label)
        }
        Text(label, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun GroupItem(groupName: String, members: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(groupName, fontWeight = FontWeight.Medium)
            Text(members, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

