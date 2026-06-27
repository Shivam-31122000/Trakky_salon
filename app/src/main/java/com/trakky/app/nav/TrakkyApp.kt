package com.trakky.app.nav

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trakky.app.ui.screens.*
import com.trakky.app.ui.theme.*

private data class Tab(val route: String, val label: String, val icon: ImageVector)

private val tabs = listOf(
    Tab("home", "Home", Icons.Filled.Home),
    Tab("nearby", "Nearby", Icons.Filled.Place),
    Tab("bookings", "Bookings", Icons.Filled.DateRange),
    Tab("profile", "Me", Icons.Filled.Person),
)

@Composable
fun TrakkyApp() {
    val nav = rememberNavController()
    var splash by remember { mutableStateOf(true) }
    val backEntry by nav.currentBackStackEntryAsState()
    val current = backEntry?.destination?.route ?: "home"
    val showBar = tabs.any { it.route == current }

    if (splash) {
        SplashScreen { splash = false }
        return
    }

    Scaffold(
        containerColor = Onyx,
        bottomBar = { if (showBar) GlassBar(current) { nav.navigate(it) { launchSingleTop = true; popUpTo("home") } } }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = "home",
            modifier = Modifier.fillMaxSize().padding(padding),
            enterTransition = { fadeIn(tween(300)) + slideInHorizontally(tween(300)) { it / 6 } },
            exitTransition = { fadeOut(tween(200)) },
            popEnterTransition = { fadeIn(tween(250)) },
            popExitTransition = { fadeOut(tween(200)) + slideOutHorizontally(tween(250)) { it / 6 } },
        ) {
            composable("home") {
                HomeScreen(
                    onOpenSalon = { nav.navigate("salon/$it") },
                    onOpenNearby = { nav.navigate("nearby") }
                )
            }
            composable("nearby") { NearbyScreen(onOpenSalon = { nav.navigate("salon/$it") }) }
            composable("bookings") { BookingsScreen() }
            composable("profile") { ProfileScreen() }
            composable("salon/{id}") { entry ->
                val id = entry.arguments?.getString("id") ?: "1"
                SalonDetailScreen(salonId = id, onBack = { nav.popBackStack() })
            }
        }
    }
}

@Composable
private fun GlassBar(current: String, onPick: (String) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .shadow(20.dp, RoundedCornerShape(40.dp), ambientColor = Gold, spotColor = Gold)
            .clip(RoundedCornerShape(40.dp))
            .background(Brush.horizontalGradient(listOf(Charcoal, DeepBurgundy.copy(alpha = 0.85f), Charcoal)))
            .padding(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { t ->
                val sel = t.route == current
                Surface(
                    onClick = { onPick(t.route) },
                    color = if (sel) Gold else androidx.compose.ui.graphics.Color.Transparent,
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Row(
                        Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(t.icon, contentDescription = t.label, tint = if (sel) Onyx else Ivory)
                        if (sel) {
                            Spacer(Modifier.width(6.dp))
                            Text(t.label, color = Onyx)
                        }
                    }
                }
            }
        }
    }
}
