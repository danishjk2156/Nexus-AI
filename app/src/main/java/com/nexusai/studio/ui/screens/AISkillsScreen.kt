package com.nexusai.studio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nexusai.studio.ui.components.*
import com.nexusai.studio.ui.theme.*

/**
 * Screen 7 — AI Skills
 * One-tap template gallery: category chips, skill cards grid,
 * search bar, bottom nav.
 */
@Composable
fun AISkillsScreen(
    selectedNavRoute: String,
    onNavSelected: (String) -> Unit,
    onSkillTap: (String) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf("For you") }
    val categories = listOf("For you", "Reels", "YouTube", "Study")

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
        ) {
            FakeStatusBar()

            // ── Title ───────────────────────────────────────────────────
            Text(
                "AI Skills",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MistPrimary,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            // ── Category Chips ──────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { cat ->
                    LagoonChip(
                        text = cat,
                        onClick = { selectedCategory = cat },
                        selected = selectedCategory == cat,
                        accentColor = Lime
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Skills Grid ─────────────────────────────────────────────
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    SkillCard(
                        title = "Create Shorts",
                        subtitle = "Long to viral clips",
                        gradientColors = listOf(Color(0xFF6B1FA0), Color(0xFFB86BFF)),
                        icon = Icons.Default.ContentCut,
                        onClick = { onSkillTap("shorts") }
                    )
                }
                item {
                    SkillCard(
                        title = "Auto Podcast",
                        subtitle = "Video to audio",
                        gradientColors = listOf(Color(0xFF1A5D0F), Color(0xFF4ADE80)),
                        icon = Icons.Default.Podcasts,
                        onClick = { onSkillTap("podcast") }
                    )
                }
                item {
                    SkillCard(
                        title = "Translate",
                        subtitle = "99+ languages",
                        gradientColors = listOf(Color(0xFF0F3D5D), Color(0xFF64C8FF)),
                        icon = Icons.Default.Translate,
                        onClick = { onSkillTap("translate") }
                    )
                }
                item {
                    SkillCard(
                        title = "Sports Highlights",
                        subtitle = "Finds big moments",
                        gradientColors = listOf(Color(0xFF5D3D0F), Color(0xFFFBBF24)),
                        icon = Icons.Default.SportsScore,
                        onClick = { onSkillTap("sports") }
                    )
                }
                item {
                    SkillCard(
                        title = "Background Music",
                        subtitle = "AI picks the mood",
                        gradientColors = listOf(Color(0xFF3D0F5D), Color(0xFFD4A5FF)),
                        icon = Icons.Default.MusicNote,
                        onClick = { onSkillTap("music") }
                    )
                }
                item {
                    SkillCard(
                        title = "Remove Silence",
                        subtitle = "Cuts dead air",
                        gradientColors = listOf(Color(0xFF0F2D3D), Color(0xFF3EA8E0)),
                        icon = Icons.Default.VolumeOff,
                        onClick = { onSkillTap("silence") }
                    )
                }
            }
        }
    }
}
