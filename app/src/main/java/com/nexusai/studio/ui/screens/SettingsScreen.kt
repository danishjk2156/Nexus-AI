package com.nexusai.studio.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 8 — Settings
 * Trust & storage: user profile, offline banner,
 * storage, AI models, performance mode, export quality.
 */
@Composable
fun SettingsScreen(
    selectedNavRoute: String,
    onNavSelected: (String) -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = DeepLagoon,
        bottomBar = {
            LagoonBottomBar(
                items = bottomNavItems,
                selectedRoute = selectedNavRoute,
                onItemSelected = onNavSelected
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            FakeStatusBar()

            Spacer(Modifier.height(16.dp))

            // ── User Profile ────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(SurfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "S",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Lime
                    )
                }
                Column {
                    Text(
                        "Salman",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MistPrimary
                    )
                    Text(
                        "salman@example.com",
                        style = MaterialTheme.typography.bodySmall,
                        color = MistTertiary
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // ── Fully Offline Banner ────────────────────────────────────
            LagoonCard(
                modifier = Modifier.padding(horizontal = 24.dp),
                onClick = {}
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            "Fully offline",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Lime
                        )
                        Text(
                            "Every AI feature works without internet",
                            style = MaterialTheme.typography.bodySmall,
                            color = MistTertiary
                        )
                    }
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = MistTertiary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Settings list ───────────────────────────────────────────
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                SettingsRow(
                    title = "Storage",
                    subtitle = "Models · Projects · Cache",
                    trailingText = "4.2 of 16 GB",
                    onClick = {}
                )

                Divider(color = SurfaceVariant, thickness = 0.5.dp)

                SettingsRow(
                    title = "AI models",
                    trailingText = "3 installed ›",
                    onClick = {}
                )

                Divider(color = SurfaceVariant, thickness = 0.5.dp)

                SettingsRow(
                    title = "Performance mode",
                    trailingChip = "Fast",
                    chipColor = Lime,
                    onClick = {}
                )

                Divider(color = SurfaceVariant, thickness = 0.5.dp)

                SettingsRow(
                    title = "Export quality",
                    trailingText = "1080p",
                    onClick = {}
                )

                Divider(color = SurfaceVariant, thickness = 0.5.dp)

                SettingsRow(
                    title = "Help & feedback",
                    onClick = {}
                )
            }

            Spacer(Modifier.height(28.dp))

            // ── App Info ────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            Brush.linearGradient(listOf(Lime, Sky))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "N",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = DeepLagoon
                    )
                }
                Column {
                    Text(
                        "NexusAI Studio 1.0",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MistPrimary
                    )
                    Text(
                        "Built for creators",
                        style = MaterialTheme.typography.bodySmall,
                        color = MistTertiary
                    )
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}
