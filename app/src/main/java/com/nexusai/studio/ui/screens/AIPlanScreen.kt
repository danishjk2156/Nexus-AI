package com.nexusai.studio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 3 — AI Plan
 * Shows the AI's plan before execution: video preview, plan steps,
 * add/change/apply controls, voice input bar.
 */
@Composable
fun AIPlanScreen(
    onApplyPlan: () -> Unit,
    onChange: () -> Unit,
    onBack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepLagoon)
            .verticalScroll(scrollState)
    ) {
        FakeStatusBar()

        // ── Top bar ─────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MistPrimary)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Travel_Vlog.mp4",
                    style = MaterialTheme.typography.titleSmall,
                    color = MistPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                "0:45",
                style = MaterialTheme.typography.bodySmall,
                color = MistTertiary
            )
        }

        // ── Video Preview ───────────────────────────────────────────────
        VideoThumbnail(
            modifier = Modifier.padding(horizontal = 24.dp),
            showPlayButton = false,
            gradient = listOf(
                Color(0xFF1A0533),
                Color(0xFF3D1F80),
                Color(0xFF6B3FA0),
                Color(0xFFD4A5FF).copy(alpha = 0.4f),
            )
        )

        Spacer(Modifier.height(4.dp))

        // Subtitle overlay indicator
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .background(DeepLagoonLight)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "यह एक अद्भितमराजीरक पल",
                style = MaterialTheme.typography.bodySmall,
                color = MistSecondary
            )
        }

        Spacer(Modifier.height(16.dp))

        // ── Command description ─────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Surface)
                .border(1.dp, Sky.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Text(
                "\"Find the action highlights and add Hindi subtitles\"",
                style = MaterialTheme.typography.bodyLarge,
                color = Sky,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(20.dp))

        // ── Plan Steps ──────────────────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                "Here's my plan",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MistPrimary
            )
            Spacer(Modifier.height(12.dp))

            PlanStepRow(text = "Find 5 highlights", isCompleted = true)
            PlanStepRow(text = "Hindi subtitles, auto-synced", isCompleted = true)

            // Add filter option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = Sky,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    "Add a vivid filter?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Sky
                )
            }

            Spacer(Modifier.height(8.dp))

            // Time / battery estimate
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = MistTertiary, modifier = Modifier.size(14.dp))
                    Text("~2 min", style = MaterialTheme.typography.bodySmall, color = MistTertiary)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Default.BatteryChargingFull, contentDescription = null, tint = MistTertiary, modifier = Modifier.size(14.dp))
                    Text("~3% battery", style = MaterialTheme.typography.bodySmall, color = MistTertiary)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── Action Buttons ──────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LagoonOutlineButton(
                text = "Change",
                onClick = onChange,
                modifier = Modifier.weight(1f)
            )
            LimeCTAButton(
                text = "Apply plan",
                onClick = onApplyPlan,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(20.dp))

        // ── Voice input bar ─────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Surface)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = MistTertiary, modifier = Modifier.size(20.dp))
                Text(
                    "Type or hold to talk...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MistTertiary
                )
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Lime),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Mic,
                    contentDescription = "Record",
                    tint = DeepLagoon,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}
