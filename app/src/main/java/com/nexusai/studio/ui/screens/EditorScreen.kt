package com.nexusai.studio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 5 — Editor
 * AI-aware timeline editor with video preview, Before|After toggle,
 * timeline clips, AI change button, and bottom tool bar.
 */
@Composable
fun EditorScreen(
    onExport: () -> Unit,
    onBack: () -> Unit,
    onAskAI: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepLagoon)
    ) {
        FakeStatusBar()

        // ── Top bar ─────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MistPrimary)
            }
            Text(
                "Goa Trip",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MistPrimary
            )
            Button(
                onClick = onExport,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Lime,
                    contentColor = DeepLagoon
                ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text("Export", fontWeight = FontWeight.Bold)
            }
        }

        // ── Before | After toggle ───────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Surface),
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Lime)
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        "Before | After",
                        style = MaterialTheme.typography.labelMedium,
                        color = DeepLagoon,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // ── Video Preview ───────────────────────────────────────────────
        VideoThumbnail(
            modifier = Modifier.padding(horizontal = 24.dp),
            showPlayButton = true,
            gradient = listOf(
                Color(0xFF1A0533),
                Color(0xFF2D1B69),
                Color(0xFF5A3D9A),
                Color(0xFFD4A5FF).copy(alpha = 0.3f),
            )
        )

        // Subtitle overlay
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "यह एक अद्भितमराजीरक पल",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }

        Spacer(Modifier.height(8.dp))

        // ── Timestamp ───────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("0:04", style = MaterialTheme.typography.labelSmall, color = MistTertiary)
            Text("0:18", style = MaterialTheme.typography.labelSmall, color = MistTertiary)
        }

        Spacer(Modifier.height(8.dp))

        // ── Timeline ────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            // Playhead indicator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Lime)
            )

            Spacer(Modifier.height(4.dp))

            // Timeline row - scrollable clips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TimelineClip(
                    color = Color(0xFF2D1B69),
                    label = "Intro",
                    modifier = Modifier.width(60.dp)
                )
                TimelineClip(
                    color = Color(0xFF4A2C8A),
                    label = "Scene 1",
                    isHighlight = true,
                    modifier = Modifier.width(80.dp)
                )
                TimelineClip(
                    color = Color(0xFF6B3FA0),
                    label = "Scene 2",
                    modifier = Modifier.width(70.dp)
                )
                TimelineClip(
                    color = Color(0xFF2D1B69),
                    label = "Scene 3",
                    modifier = Modifier.width(65.dp)
                )
                TimelineClip(
                    color = Color(0xFF4A2C8A),
                    label = "End",
                    modifier = Modifier.width(50.dp)
                )
            }

            Spacer(Modifier.height(6.dp))

            // Audio / subtitle tracks
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Lime.copy(alpha = 0.2f))
                        .padding(horizontal = 6.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text("Subtitles", style = MaterialTheme.typography.labelSmall, color = Lime)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Sky.copy(alpha = 0.15f))
                        .padding(horizontal = 6.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text("Audio", style = MaterialTheme.typography.labelSmall, color = Sky)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // ── AI Change Bar ───────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Surface)
                    .clickable(onClick = onAskAI)
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Sky,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    "Ask AI to change...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MistTertiary
                )
            }

            Spacer(Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Surface)
                    .clickable { }
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(
                    "tap a clip",
                    style = MaterialTheme.typography.labelMedium,
                    color = MistSecondary
                )
            }
        }

        Spacer(Modifier.weight(1f))

        // ── Bottom Tool Bar ─────────────────────────────────────────────
        Divider(color = SurfaceVariant, thickness = 0.5.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepLagoonLight)
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            EditorToolButton(icon = Icons.Default.ContentCut, label = "Trim")
            EditorToolButton(icon = Icons.Default.FilterVintage, label = "Filters")
            EditorToolButton(icon = Icons.Default.TextFields, label = "Text")
            EditorToolButton(icon = Icons.Default.MusicNote, label = "Music")
            EditorToolButton(icon = Icons.Default.RecordVoiceOver, label = "Voice")
        }
    }
}

@Composable
private fun EditorToolButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(icon, contentDescription = label, tint = MistSecondary, modifier = Modifier.size(22.dp))
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, color = MistTertiary)
    }
}
