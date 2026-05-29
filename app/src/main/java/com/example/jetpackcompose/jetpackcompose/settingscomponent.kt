package com.example.jetpackcompose.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingsTopBar(title: String, onBack: () -> Unit) {
    Column {
        Box(
            modifier         = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick  = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp)
            ) {
                Box(
                    modifier         = Modifier
                        .size(36.dp)
                        .background(LavenderFaint, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go back",
                        tint               = LavenderDeep,
                        modifier           = Modifier.size(18.dp)
                    )
                }
            }
            Text(
                text       = title,
                fontSize   = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color      = Color.Black
            )
        }
        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 0.8.dp)
    }
}

// ─────────────────────────────────────────────────────────────
//  SECTION LABEL
// ─────────────────────────────────────────────────────────────
@Composable
fun SectionLabel(text: String) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text          = text.uppercase(),
            fontSize      = 11.sp,
            fontWeight    = FontWeight.SemiBold,
            color         = LavenderDeep,
            letterSpacing = 0.8.sp
        )
        Spacer(Modifier.width(8.dp))
        HorizontalDivider(
            modifier  = Modifier.weight(1f),
            color     = Color(0xFFEEEEEE),
            thickness = 0.8.dp
        )
    }
}

// ─────────────────────────────────────────────────────────────
//  SETTINGS CARD
// ─────────────────────────────────────────────────────────────
@Composable
fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    Surface(
        modifier        = Modifier.fillMaxWidth(),
        shape           = RoundedCornerShape(16.dp),
        color           = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(content = content)
    }
}

// ─────────────────────────────────────────────────────────────
//  CARD DIVIDER
// ─────────────────────────────────────────────────────────────
@Composable
fun CardDivider() = HorizontalDivider(
    modifier  = Modifier.padding(start = 70.dp),
    color     = Color(0xFFF0EEFF),
    thickness = 0.8.dp
)

// ─────────────────────────────────────────────────────────────
//  NAV ROW
// ─────────────────────────────────────────────────────────────
@Composable
fun NavRow(
    icon    : ImageVector,
    label   : String,
    subtitle: String = "",
    tint    : Color  = LavenderDeep,
    onClick : () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(40.dp)
                .background(tint.copy(alpha = 0.10f), CircleShape),
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
            Text(label, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            if (subtitle.isNotEmpty())
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

// ─────────────────────────────────────────────────────────────
//  TOGGLE ROW
// ─────────────────────────────────────────────────────────────
@Composable
fun ToggleRow(
    icon    : ImageVector,
    label   : String,
    subtitle: String  = "",
    tint    : Color   = LavenderDeep,
    checked : Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(40.dp)
                .background(tint.copy(alpha = 0.10f), CircleShape),
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
            Text(label, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            if (subtitle.isNotEmpty())
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Switch(
            checked         = checked,
            onCheckedChange = onToggle,
            colors          = SwitchDefaults.colors(
                checkedThumbColor   = Color.White,
                checkedTrackColor   = Lavender,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFDDDDDD)
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────
//  VALUE ROW
// ─────────────────────────────────────────────────────────────
@Composable
fun ValueRow(
    icon    : ImageVector,
    label   : String,
    value   : String,
    tint    : Color = LavenderDeep,
    onClick : () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(40.dp)
                .background(tint.copy(alpha = 0.10f), CircleShape),
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
        Text(
            text       = label,
            fontSize   = 15.sp,
            fontWeight = FontWeight.Medium,
            color      = Color.Black,
            modifier   = Modifier.weight(1f)
        )
        Text(value, fontSize = 13.sp, color = Color.Gray)
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector        = Icons.Default.ChevronRight,
            contentDescription = null,
            tint               = Color(0xFFCCCCCC),
            modifier           = Modifier.size(20.dp)
        )
    }
}