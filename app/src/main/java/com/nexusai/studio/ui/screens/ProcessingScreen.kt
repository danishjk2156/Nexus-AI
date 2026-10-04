package com.nexusai.studio.ui.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 4 — Processing
 * Live, pausable on-device processing.
 * Shows a large circular progress, step checklist,
 * device stats, and Pause/Cancel buttons.
 */
@Composable
fun ProcessingScreen(
    onComplete: () -> Unit = {},
    onPause: () -> Unit,
    onCancel: () -> Unit,
) {
    val scrollState = rememberScrollState()

    // Simulated animated progress
    val infiniteTransition = rememberInfiniteTransition(label = "processingPulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepLagoon)
            .verticalScroll(scrollState)
    ) {
        FakeStatusBar()

        // ── Header ──────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                "Editing on your device",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MistPrimary
            )
            Text(
                "Feel free to leave — we'll notify you.",
                style = MaterialTheme.typography.bodyMedium,
                color = MistSecondary
            )
        }

        Spacer(Modifier.height(8.dp))

        // ── Highlight indicator ─────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            LagoonChip(
                text = "Highlight 2 of 5",
                onClick = {},
                selected = true,
                accentColor = Lime,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        Spacer(Modifier.height(8.dp))

        // ── Video preview area ──────────────────────────────────────────
        VideoThumbnail(
            modifier = Modifier.padding(horizontal = 24.dp),
            showPlayButton = false,
            gradient = listOf(
                Color(0xFF1A0533),
                Color(0xFF2D1B69),
                Color(0xFF5A3D9A),
                Color(0xFFD4A5FF).copy(alpha = 0.3f),
            )
        )

        Spacer(Modifier.height(24.dp))

        // ── Circular Progress ───────────────────────────────────────────
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicatorWithText(
                progress = 0.72f,
                text = "72%",
                subtitle = "about 40 sec left"
            )
        }

        Spacer(Modifier.height(20.dp))

        // ── Step checklist ──────────────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            ProcessingStepRow(
                text = "Speech analysed",
                isDone = true,
                pulseAlpha = 1f
            )
            ProcessingStepRow(
                text = "Highlights found",
                isDone = true,
                pulseAlpha = 1f
            )
            ProcessingStepRow(
                text = "Hindi subtitles",
                isDone = false,
                pulseAlpha = pulseAlpha
            )
            ProcessingStepRow(
                text = "Effects & export",
                isDone = false,
                pulseAlpha = 0.3f
            )
        }

        Spacer(Modifier.height(20.dp))

        // ── Device stats ────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Cool · 81% battery · GPU",
                style = MaterialTheme.typography.bodySmall,
                color = MistTertiary
            )
            LagoonChip(
                text = "Eco",
                onClick = {},
                selected = true,
                accentColor = Lime
            )
        }

        Spacer(Modifier.height(24.dp))

        // ── Action Buttons ──────────────────────────────────────────────
        LimeCTAButton(
            text = "Open in Editor",
            onClick = onComplete,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LagoonOutlineButton(
                text = "Pause",
                onClick = onPause,
                modifier = Modifier.weight(1f)
            )
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Coral
                ),
                border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                    brush = androidx.compose.ui.graphics.Brush.linearGradient(listOf(Coral, Coral))
                ),
                shape = RoundedCornerShape(14.dp),
            ) {
                Text(
                    "Cancel",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun ProcessingStepRow(
    text: String,
    isDone: Boolean,
    pulseAlpha: Float,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (isDone) {
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Lime,
                modifier = Modifier.size(18.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(MistTertiary.copy(alpha = pulseAlpha * 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (pulseAlpha > 0.5f) Sky.copy(alpha = pulseAlpha)
                            else MistDisabled
                        )
                )
            }
        }
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isDone) MistPrimary else MistSecondary.copy(alpha = if (pulseAlpha > 0.5f) 1f else 0.5f)
        )
    }
}

