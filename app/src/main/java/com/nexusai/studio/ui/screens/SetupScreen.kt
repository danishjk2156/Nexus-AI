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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 1 — Setup
 * One-time model download & device check.
 * Shows model download progress, device capabilities, and a "Start with what's ready" CTA.
 */
@Composable
fun SetupScreen(
    onStartClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepLagoon)
            .verticalScroll(scrollState)
    ) {
        FakeStatusBar()

        Spacer(Modifier.height(16.dp))

        // ── Hero header ─────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            // App icon
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Lime, Sky)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "N",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    color = DeepLagoon
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Set up your offline\nstudio",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MistPrimary,
                lineHeight = 34.sp
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "One download. Then no internet, ever.",
                style = MaterialTheme.typography.bodyLarge,
                color = MistSecondary
            )
        }

        Spacer(Modifier.height(24.dp))

        // ── Model download cards ────────────────────────────────────────
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ModelDownloadRow(
                name = "Whisper · speech",
                size = "240 MB",
                subtitle = "Speech-to-text model",
                progress = 1f  // completed
            )

            ModelDownloadRow(
                name = "Gemma · understands you",
                size = "1.4 GB · about 3 min on Wi-Fi",
                subtitle = "Language understanding model",
                progress = 0.62f
            )
        }

        Spacer(Modifier.height(16.dp))

        // ── Device check ────────────────────────────────────────────────
        LagoonCard(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Lime.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Lime,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Column {
                    Text(
                        "Device check passed",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MistPrimary
                    )
                    Text(
                        "6 GB RAM · GPU on · Fast mode",
                        style = MaterialTheme.typography.bodySmall,
                        color = MistTertiary
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // ── CTA ─────────────────────────────────────────────────────────
        LimeCTAButton(
            text = "Start with what's ready",
            onClick = onStartClick,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(12.dp))

        // ── Privacy note ────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Lock,
                contentDescription = null,
                tint = MistTertiary,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "Your videos never leave this phone",
                style = MaterialTheme.typography.bodySmall,
                color = MistTertiary
            )
        }

        Spacer(Modifier.height(32.dp))
    }
}
