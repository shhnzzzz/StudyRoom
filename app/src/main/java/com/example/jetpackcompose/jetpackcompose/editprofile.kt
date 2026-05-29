package com.example.jetpackcompose.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfile(
    onSave: () -> Unit = {},
    onCancel: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var name     by remember { mutableStateOf("Shahnaz") }
    var username by remember { mutableStateOf("shahnaz_dev") }
    var bio      by remember { mutableStateOf("Computer Science student passionate about Android.") }
    var birthday by remember { mutableStateOf("01/01/2004") }
    var email    by remember { mutableStateOf("xyz@gmail.com") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            // Pushes content below the status bar and above the nav bar
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        // ── Top bar (56 dp — standard height, back button has full 48dp tap zone) ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            // 48×48 tap target wrapping the visible 36×36 circle
            IconButton(
                onClick  = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp)          // ← full tap target, meets Material guidelines
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = Color(0xFFD2B4FC).copy(alpha = 0.22f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector        = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Go back",
                        tint               = Color(0xFF7C4DCC),
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }

            Text(
                text       = "Edit Profile",
                fontSize   = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color      = Color.Black
            )
        }

        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 0.8.dp)

        // ── Scrollable body ──────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 28.dp)
                .padding(top = 20.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Avatar
            Box(contentAlignment = Alignment.BottomEnd) {
                Surface(
                    modifier = Modifier.size(100.dp),
                    shape    = CircleShape,
                    color    = Color(0xFFD9DADB).copy(alpha = 0.5f)
                ) {
                    Icon(
                        imageVector        = Icons.Default.Person,
                        contentDescription = null,
                        modifier           = Modifier.padding(20.dp),
                        tint               = Color.Gray
                    )
                }
                IconButton(
                    onClick  = { /* Open Image Picker */ },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFD2B4FC), CircleShape)
                ) {
                    Icon(
                        imageVector        = Icons.Default.PhotoCamera,
                        contentDescription = "Change photo",
                        modifier           = Modifier.size(16.dp),
                        tint               = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text       = "Change photo",
                fontSize   = 13.sp,
                color      = Color(0xFF7C4DCC),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Fields
            EditField(value = name,     onValueChange = { name = it },     label = "Full Name")
            EditField(value = username, onValueChange = { username = it }, label = "Username")
            EditField(value = bio,      onValueChange = { bio = it },      label = "Bio", isSingleLine = false)
            EditField(
                value = birthday, onValueChange = { birthday = it }, label = "Birthday",
                trailingIcon = { Icon(Icons.Default.Cake,  contentDescription = null, tint = Color(0xFFD2B4FC)) }
            )
            EditField(
                value = email, onValueChange = { email = it }, label = "Email",
                trailingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFFD2B4FC)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick   = onSave,
                modifier  = Modifier.fillMaxWidth().height(52.dp),
                colors    = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2B4FC)),
                shape     = RoundedCornerShape(14.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text("Save Changes", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            TextButton(onClick = onCancel, modifier = Modifier.padding(top = 4.dp)) {
                Text("Discard Changes", color = Color.Red.copy(alpha = 0.65f), fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun EditField(
    value         : String,
    onValueChange : (String) -> Unit,
    label         : String,
    isSingleLine  : Boolean = true,
    trailingIcon  : @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value         = value,
        onValueChange = onValueChange,
        label         = { Text(label, fontSize = 14.sp) },
        modifier      = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        singleLine    = isSingleLine,
        minLines      = if (isSingleLine) 1 else 3,
        trailingIcon  = trailingIcon,
        shape         = RoundedCornerShape(12.dp),
        colors        = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = Color(0xFFD2B4FC),
            unfocusedBorderColor = Color(0xFFDDDDDD),
            focusedLabelColor    = Color(0xFF7C4DCC),
            unfocusedLabelColor  = Color.Gray,
            cursorColor          = Color(0xFFD2B4FC)
        )
    )
}