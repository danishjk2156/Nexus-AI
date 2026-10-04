package com.nexusai.studio.ui.screens

import androidx.compose.animation.*
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
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 2 — Home
 * Voice-first landing: greeting, mic/type input, skill chips,
 * recent projects, bottom nav.
 */
@Composable
fun HomeScreen(
    onMicTap: () -> Unit,
    onChipTap: (String) -> Unit,
    onProjectTap: () -> Unit,
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

            // ── Greeting ────────────────────────────────────────────────
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Good evening, Salman",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MistSecondary
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "What shall we make?",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = MistPrimary
                )
            }

            Spacer(Modifier.height(20.dp))

            // ── Voice / Type Input ──────────────────────────────────────
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Surface)
                    .border(1.dp, MistDisabled, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Default.Mic,
                            contentDescription = "Tap mic",
                            tint = Sky,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            "Tap the mic or type",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MistTertiary
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "\"Make a 30s reel from my Goa trip...\"",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = Lime.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Skill Chips ─────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                LagoonChip(
                    text = "Highlights",
                    onClick = { onChipTap("highlights") },
                    icon = Icons.Default.AutoAwesome,
                    accentColor = Sky,
                    selected = false
                )
                LagoonChip(
                    text = "CC Subtitles",
                    onClick = { onChipTap("subtitles") },
                    icon = Icons.Default.ClosedCaption,
                    accentColor = Sky,
                    selected = false
                )
                LagoonChip(
                    text = "Translate",
                    onClick = { onChipTap("translate") },
                    icon = Icons.Default.Translate,
                    accentColor = Sky,
                    selected = false
                )
            }

            Spacer(Modifier.height(24.dp))

            // ── Continue Editing ─────────────────────────────────────────
            SectionHeader(
                title = "Continue editing",
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Project card 1
            LagoonCard(
                modifier = Modifier.padding(horizontal = 24.dp),
                onClick = onProjectTap
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Thumbnail
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF2D1B69),
                                        Color(0xFFB86BFF).copy(alpha = 0.6f)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Videocam,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Goa Trip",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MistPrimary
                        )
                        Text(
                            "Subtitles · 72% done",
                            style = MaterialTheme.typography.bodySmall,
                            color = MistTertiary
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            // Project card 2
            LagoonCard(
                modifier = Modifier.padding(horizontal = 24.dp),
                onClick = onProjectTap
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF1A3D0F),
                                        Color(0xFF4ADE80).copy(alpha = 0.5f)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.SportsFootball,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Football Match",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MistPrimary
                        )
                        Text(
                            "Edited yesterday",
                            style = MaterialTheme.typography.bodySmall,
                            color = MistTertiary
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Offline indicator ───────────────────────────────────────
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Lime)
                )
                Text(
                    "Offline  ·  4.2 GB models  ·  12 GB free",
                    style = MaterialTheme.typography.bodySmall,
                    color = MistTertiary
                )
            }

            Spacer(Modifier.height(24.dp))

            // ── Mic FAB (centered) ──────────────────────────────────────
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                FloatingActionButton(
                    onClick = onMicTap,
                    containerColor = Lime,
                    contentColor = DeepLagoon,
                    shape = CircleShape,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        Icons.Default.Mic,
                        contentDescription = "Start recording",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

// ── Shared bottom nav items ─────────────────────────────────────────────────
val bottomNavItems = listOf(
    NavItem("Home", Icons.Filled.Home, "home"),
    NavItem("Skills", Icons.Filled.AutoAwesome, "skills"),
    NavItem("Projects", Icons.Filled.Folder, "projects"),
    NavItem("More", Icons.Filled.MoreHoriz, "more"),
)
