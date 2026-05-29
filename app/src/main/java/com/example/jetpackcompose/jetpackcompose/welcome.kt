package com.example.jetpackcompose.jetpackcompose

import android.text.Layout
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.MyCustomFont
import java.nio.file.WatchEvent


@Composable
 fun WelcomePage(
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit
 ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE0B0FF), // Light Purple top-left
                        Color(0xFFFFD1DC)  // Light Pink bottom-right
                    )
                )
            )
            .padding(top = 48.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- TOP AREA: Header Text ---
            Text(
                text = "Welcome",
                fontFamily = MyCustomFont,
                fontSize= 60.sp,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "\" Your study space, your people.\" ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp
            )

            // --- SPACER FOR MIDDLE AREA ---
            // In the reference image_1.png, this space is used by the illustration.
            // We use a large Spacer to push the buttons to the bottom as requested.
            Spacer(modifier = Modifier.weight(1f))


            // --- BOTTOM AREA: Action Buttons ---
            // Container for the primary 'Create Account' button (Solid white with pink gradient text)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp), // Pill shape
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                // Since this button has complex text colors and content, we use a custom composable structure
                // inside a selectable container rather than just a simple Button().
                Button (
                    onClick = { onNavigateToSignUp() }, // Triggers the navigation!
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, // White background
                        contentColor = Color.Unspecified // Allows our gradient text to shine
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        text = "Create Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium.copy(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFE0B0FF), Color(0xFFFFD1DC))
                            )
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // The 'Log In' button (Outlined with pink/purple text)
            OutlinedButton (
                onClick = { onNavigateToLogin()},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFC080E0) // Matching the gradient theme color
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFD1DC)) // Pinkish border
            ) {
                Text(
                    text = "Log In",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            // Bottom padding to ensure the buttons don't clip the screen edge
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
@Composable
fun SimpleIconPlaceholder() {
    Surface(
        modifier = Modifier.width(16.dp).height(16.dp),
        shape = RoundedCornerShape(4.dp),
        color = Color.DarkGray
    ) {}
}



    
