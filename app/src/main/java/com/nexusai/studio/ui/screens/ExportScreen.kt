package com.nexusai.studio.ui.screens

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 6 — Export
 * Share-first screen: "Your reel is ready", video preview,
 * metadata (length, size, quality), share targets, save to gallery.
 */
@Composable
fun ExportScreen(
    onShare: () -> Unit,
    onSaveToGallery: () -> Unit,
    onTryAnotherStyle: () -> Unit,
    onKeepEditing: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepLagoon)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FakeStatusBar()

        Spacer(Modifier.height(24.dp))

        // ── Checkmark icon ──────────────────────────────────────────────
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Lime.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                tint = Lime,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        // ── Title ───────────────────────────────────────────────────────
        Text(
            "Your reel is ready",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MistPrimary,
            textAlign = TextAlign.Center
        )
        Text(
            "Made 100% on your phone",
            style = MaterialTheme.typography.bodyMedium,
            color = MistSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(20.dp))

        // ── Video preview ───────────────────────────────────────────────
        VideoThumbnail(
            modifier = Modifier.padding(horizontal = 24.dp),
            showPlayButton = true,
            gradient = listOf(
                Color(0xFF1A0533),
                Color(0xFF2D1B69),
                Color(0xFF5A3D9A),
                Color(0xFFB86BFF).copy(alpha = 0.4f),
            )
        )

        Spacer(Modifier.height(20.dp))

        // ── Metadata chips ──────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MetadataPill(label = "0:18", sublabel = "Length")
            MetadataPill(label = "24 MB", sublabel = "Size")
            MetadataPill(label = "1080p", sublabel = "Quality")
        }

        Spacer(Modifier.height(24.dp))

        // ── Share targets ───────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ShareTarget(name = "WhatsApp", icon = Icons.Default.Chat)
            ShareTarget(name = "Instagram", icon = Icons.Default.CameraAlt)
            ShareTarget(name = "YouTube", icon = Icons.Default.PlayCircle)
        }

        Spacer(Modifier.height(20.dp))

        // ── Share CTA ───────────────────────────────────────────────────
        LimeCTAButton(
            text = "Share",
            onClick = onShare,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(12.dp))

        // ── Save to Gallery ─────────────────────────────────────────────
        LagoonOutlineButton(
            text = "Save to Gallery",
            onClick = onSaveToGallery,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(20.dp))

        // ── Footer links ────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = onTryAnotherStyle) {
                Icon(Icons.Default.Palette, contentDescription = null, tint = Sky, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Try another style", color = Sky, style = MaterialTheme.typography.labelLarge)
            }
            TextButton(onClick = onKeepEditing) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = MistSecondary, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Keep editing", color = MistSecondary, style = MaterialTheme.typography.labelLarge)
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun MetadataPill(label: String, sublabel: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MistPrimary
        )
        Text(
            sublabel,
            style = MaterialTheme.typography.labelSmall,
            color = MistTertiary
        )
    }
}

@Composable
private fun ShareTarget(
    name: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Surface),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = name, tint = MistPrimary, modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.height(6.dp))
        Text(name, style = MaterialTheme.typography.labelSmall, color = MistSecondary)
    }
}
