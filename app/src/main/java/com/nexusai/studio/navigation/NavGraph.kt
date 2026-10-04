package com.nexusai.studio.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nexusai.studio.ui.screens.*

/**
 * Navigation routes matching the 8-screen user workflow:
 *  Setup → Home ↔ AI Plan → Processing → Editor → Export
 *                ↔ AI Skills
 *                ↔ Settings (via More tab)
 */
object Routes {
    const val SETUP = "setup"
    const val HOME = "home"
    const val AI_PLAN = "ai_plan"
    const val PROCESSING = "processing"
    const val EDITOR = "editor"
    const val EXPORT = "export"
    const val AI_SKILLS = "skills"
    const val SETTINGS = "more"
}

@Composable
fun NexusNavHost(navController: NavHostController) {
    // Track which bottom nav tab is selected
    var selectedNavRoute by remember { mutableStateOf(Routes.HOME) }

    NavHost(
        navController = navController,
        startDestination = Routes.SETUP,
        enterTransition = { fadeIn(tween(300)) + slideInHorizontally(tween(300)) { it / 4 } },
        exitTransition = { fadeOut(tween(200)) },
        popEnterTransition = { fadeIn(tween(300)) + slideInHorizontally(tween(300)) { -it / 4 } },
        popExitTransition = { fadeOut(tween(200)) },
    ) {
        // ── 1. Setup ────────────────────────────────────────────────────
        composable(Routes.SETUP) {
            SetupScreen(
                onStartClick = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SETUP) { inclusive = true }
                    }
                }
            )
        }

        // ── 2. Home ─────────────────────────────────────────────────────
        composable(Routes.HOME) {
            selectedNavRoute = Routes.HOME
            HomeScreen(
                onMicTap = { navController.navigate(Routes.AI_PLAN) },
                onChipTap = { navController.navigate(Routes.AI_PLAN) },
                onProjectTap = { navController.navigate(Routes.EDITOR) },
                selectedNavRoute = selectedNavRoute,
                onNavSelected = { route ->
                    selectedNavRoute = route
                    when (route) {
                        Routes.HOME -> { /* already here */ }
                        Routes.AI_SKILLS, "skills" -> navController.navigate(Routes.AI_SKILLS) {
                            launchSingleTop = true
                        }
                        "more" -> navController.navigate(Routes.SETTINGS) {
                            launchSingleTop = true
                        }
                        else -> { /* projects tab — stays on home for prototype */ }
                    }
                }
            )
        }

        // ── 3. AI Plan ──────────────────────────────────────────────────
        composable(Routes.AI_PLAN) {
            AIPlanScreen(
                onApplyPlan = { navController.navigate(Routes.PROCESSING) },
                onChange = { /* stays on same screen */ },
                onBack = { navController.popBackStack() }
            )
        }

        // ── 4. Processing ───────────────────────────────────────────────
        composable(Routes.PROCESSING) {
            ProcessingScreen(
                onComplete = { navController.navigate(Routes.EDITOR) },
                onPause = { /* toggle pause state */ },
                onCancel = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }

        // ── 5. Editor ───────────────────────────────────────────────────
        composable(Routes.EDITOR) {
            EditorScreen(
                onExport = { navController.navigate(Routes.EXPORT) },
                onBack = { navController.popBackStack() },
                onAskAI = { navController.navigate(Routes.AI_PLAN) }
            )
        }

        // ── 6. Export ───────────────────────────────────────────────────
        composable(Routes.EXPORT) {
            ExportScreen(
                onShare = { /* share intent */ },
                onSaveToGallery = { /* save */ },
                onTryAnotherStyle = {
                    navController.navigate(Routes.AI_SKILLS) {
                        launchSingleTop = true
                    }
                },
                onKeepEditing = { navController.popBackStack() }
            )
        }

        // ── 7. AI Skills ────────────────────────────────────────────────
        composable(Routes.AI_SKILLS) {
            selectedNavRoute = Routes.AI_SKILLS
            AISkillsScreen(
                selectedNavRoute = selectedNavRoute,
                onNavSelected = { route ->
                    selectedNavRoute = route
                    when (route) {
                        Routes.HOME, "home" -> navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = true }
                        }
                        "more" -> navController.navigate(Routes.SETTINGS) {
                            launchSingleTop = true
                        }
                        else -> { }
                    }
                },
                onSkillTap = { navController.navigate(Routes.AI_PLAN) }
            )
        }

        // ── 8. Settings ─────────────────────────────────────────────────
        composable(Routes.SETTINGS) {
            selectedNavRoute = Routes.SETTINGS
            SettingsScreen(
                selectedNavRoute = selectedNavRoute,
                onNavSelected = { route ->
                    selectedNavRoute = route
                    when (route) {
                        Routes.HOME, "home" -> navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = true }
                        }
                        Routes.AI_SKILLS, "skills" -> navController.navigate(Routes.AI_SKILLS) {
                            launchSingleTop = true
                        }
                        else -> { }
                    }
                }
            )
        }
    }
}
