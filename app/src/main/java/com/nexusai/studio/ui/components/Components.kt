package com.nexusai.studio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexusai.studio.ui.theme.*

// ─── Status Bar ─────────────────────────────────────────────────────────────

@Composable
fun FakeStatusBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "9:41",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = MistPrimary
        )
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(Icons.Default.SignalCellularAlt, contentDescription = null, modifier = Modifier.size(16.dp), tint = MistPrimary)
            Icon(Icons.Default.Wifi, contentDescription = null, modifier = Modifier.size(16.dp), tint = MistPrimary)
            Icon(Icons.Default.BatteryFull, contentDescription = null, modifier = Modifier.size(16.dp), tint = MistPrimary)
        }
    }
}

// ─── Lagoon Button (Lime CTA) ───────────────────────────────────────────────

@Composable
fun LimeCTAButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Lime,
            contentColor = DeepLagoon,
            disabledContainerColor = LimeDim,
            disabledContentColor = MistDisabled
        ),
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 1.dp
        )
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

// ─── Outline Button ─────────────────────────────────────────────────────────

@Composable
fun LagoonOutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    borderColor: Color = MistTertiary,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MistPrimary
        ),
        border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
            brush = Brush.linearGradient(listOf(borderColor, borderColor))
        ),
        shape = RoundedCornerShape(14.dp),
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
        )
    }
}

// ─── Chip / Pill ────────────────────────────────────────────────────────────

@Composable
fun LagoonChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    selected: Boolean = false,
    accentColor: Color = Sky,
) {
    val bgColor = if (selected) accentColor.copy(alpha = 0.2f) else Surface
    val textColor = if (selected) accentColor else MistSecondary
    val borderMod = if (selected) {
        Modifier.border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
    } else Modifier

    Row(
        modifier = modifier
            .then(borderMod)
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp), tint = textColor)
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}

// ─── Surface Card ───────────────────────────────────────────────────────────

@Composable
fun LagoonCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(16.dp)
    val mod = modifier
        .fillMaxWidth()
        .clip(shape)
        .background(Surface)
        .then(
            if (onClick != null)
                Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = Lime),
                    onClick = onClick
                )
            else Modifier
        )
        .padding(16.dp)

    Column(modifier = mod, content = content)
}

// ─── Video Thumbnail Placeholder ────────────────────────────────────────────

@Composable
fun VideoThumbnail(
    modifier: Modifier = Modifier,
    gradient: List<Color> = listOf(
        Color(0xFF1A0533),
        Color(0xFF2D1B69),
        Color(0xFF4A2C8A),
        Color(0xFFB86BFF).copy(alpha = 0.4f),
    ),
    showPlayButton: Boolean = true,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.verticalGradient(gradient)
            ),
        contentAlignment = Alignment.Center
    ) {
        // Mountain silhouette effect
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(0f, h * 0.7f)
                lineTo(w * 0.15f, h * 0.45f)
                lineTo(w * 0.3f, h * 0.6f)
                lineTo(w * 0.5f, h * 0.3f)
                lineTo(w * 0.7f, h * 0.5f)
                lineTo(w * 0.85f, h * 0.35f)
                lineTo(w, h * 0.55f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(path, Color(0xFF0A0A1A).copy(alpha = 0.6f))
        }

        // Sun circle
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFFFD700).copy(alpha = 0.7f),
                radius = 30f,
                center = Offset(size.width * 0.75f, size.height * 0.25f)
            )
        }

        if (showPlayButton) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

// ─── Circular Progress with percentage ──────────────────────────────────────

@Composable
fun CircularProgressIndicatorWithText(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 140.dp,
    strokeWidth: Dp = 10.dp,
    trackColor: Color = SurfaceVariant,
    progressColor: Color = Lime,
    text: String = "${(progress * 100).toInt()}%",
    subtitle: String? = null,
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sweepAngle = progress * 360f
            val padding = strokeWidth.toPx() / 2
            // Track
            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = Offset(padding, padding),
                size = androidx.compose.ui.geometry.Size(
                    this.size.width - strokeWidth.toPx(),
                    this.size.height - strokeWidth.toPx()
                )
            )
            // Progress
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = Offset(padding, padding),
                size = androidx.compose.ui.geometry.Size(
                    this.size.width - strokeWidth.toPx(),
                    this.size.height - strokeWidth.toPx()
                )
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MistPrimary
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MistSecondary
                )
            }
        }
    }
}

// ─── Bottom Navigation Bar ──────────────────────────────────────────────────

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun LagoonBottomBar(
    items: List<NavItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = DeepLagoonLight,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            val selected = item.route == selectedRoute
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Lime,
                    selectedTextColor = Lime,
                    unselectedIconColor = MistTertiary,
                    unselectedTextColor = MistTertiary,
                    indicatorColor = LimeDim
                )
            )
        }
    }
}

// ─── Section Header ─────────────────────────────────────────────────────────

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    action: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MistPrimary
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MistTertiary
                )
            }
        }
        if (action != null && onAction != null) {
            TextButton(onClick = onAction) {
                Text(action, color = Sky, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

// ─── Model Download Row ─────────────────────────────────────────────────────

@Composable
fun ModelDownloadRow(
    name: String,
    size: String,
    subtitle: String,
    progress: Float?, // null = not started, 0..1 = downloading, 1 = done
    modifier: Modifier = Modifier,
) {
    val isDone = progress != null && progress >= 1f
    LagoonCard(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (isDone) Lime.copy(alpha = 0.15f) else SurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    if (isDone) Icons.Default.CheckCircle else Icons.Default.CloudDownload,
                    contentDescription = null,
                    tint = if (isDone) Lime else Sky,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(name, style = MaterialTheme.typography.titleSmall, color = MistPrimary, fontWeight = FontWeight.SemiBold)
                    if (isDone) {
                        LagoonChip(text = "Ready", onClick = {}, accentColor = Lime, selected = true)
                    }
                }
                Text(size, style = MaterialTheme.typography.bodySmall, color = MistTertiary)
                Spacer(Modifier.height(4.dp))
                if (progress != null && !isDone) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = Lime,
                            trackColor = SurfaceVariant
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "${(progress * 100).toInt()}%",
                            style = MaterialTheme.typography.labelSmall,
                            color = Lime
                        )
                    }
                } else if (!isDone) {
                    Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MistSecondary)
                }
            }
        }
    }
}

// ─── Skill Card (for AI Skills grid) ────────────────────────────────────────

@Composable
fun SkillCard(
    title: String,
    subtitle: String,
    gradientColors: List<Color>,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Surface)
            .clickable(onClick = onClick)
    ) {
        // Thumbnail
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Brush.verticalGradient(gradientColors)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(32.dp)
            )
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.labelLarge,
                color = MistPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MistTertiary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ─── Timeline Clip (for Editor) ─────────────────────────────────────────────

@Composable
fun TimelineClip(
    color: Color,
    label: String,
    isHighlight: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = if (isHighlight) 0.8f else 0.4f))
            .then(
                if (isHighlight) Modifier.border(
                    2.dp,
                    Lime,
                    RoundedCornerShape(6.dp)
                ) else Modifier
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = MistPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// ─── Settings Row ───────────────────────────────────────────────────────────

@Composable
fun SettingsRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailingText: String? = null,
    trailingChip: String? = null,
    chipColor: Color = Lime,
    icon: ImageVector? = null,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = 14.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = MistSecondary, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(14.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge, color = MistPrimary)
            if (subtitle != null) {
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MistTertiary)
            }
        }
        if (trailingText != null) {
            Text(trailingText, style = MaterialTheme.typography.bodyMedium, color = MistSecondary)
        }
        if (trailingChip != null) {
            LagoonChip(text = trailingChip, onClick = {}, selected = true, accentColor = chipColor)
        }
        if (onClick != null) {
            Spacer(Modifier.width(4.dp))
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MistTertiary, modifier = Modifier.size(20.dp))
        }
    }
}

// ─── Plan Step Row ──────────────────────────────────────────────────────────

@Composable
fun PlanStepRow(
    text: String,
    isCompleted: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (isCompleted) Lime else MistTertiary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isCompleted) MistPrimary else MistSecondary
        )
    }
}
