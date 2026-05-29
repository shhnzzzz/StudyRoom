
package com.example.studyroom.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaPickerSheet(
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            Text(
                "Share Content",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MediaIconItem(Icons.Default.Image, "Gallery", Color(0xFF4CAF50)) { onOptionSelected("IMAGE") }
                MediaIconItem(Icons.Default.CameraAlt, "Camera", Color(0xFFE91E63)) { onOptionSelected("CAMERA") }
                MediaIconItem(Icons.Default.Description, "File", Color(0xFF2196F3)) { onOptionSelected("FILE") }
            //  MediaIconItem(Icons.Default.LocationOn, "Location", Color(0xFFFF9800)) { onOptionSelected("LOCATION") }
            }
        }
    }
}

@Composable
fun MediaIconItem(icon: ImageVector, label: String, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Surface(
            shape = CircleShape,
            color = color.copy(alpha = 0.15f),
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.padding(14.dp)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}