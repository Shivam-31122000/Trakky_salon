package com.trakky.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.trakky.app.R
import com.trakky.app.ui.components.AnimatedAuroraBackground
import com.trakky.app.ui.components.ScissorLoader
import com.trakky.app.ui.theme.Gold
import com.trakky.app.ui.theme.RoseGold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onDone: () -> Unit) {
    LaunchedEffect(Unit) { delay(1800); onDone() }
    AnimatedAuroraBackground(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Image(
//                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
//                contentDescription = "App Logo",
//                modifier = Modifier.size(120.dp)
//            )
//            Spacer(Modifier.height(16.dp))
            ScissorLoader(size = 150)
            Spacer(Modifier.height(24.dp))
            Text("TRAKKY", color = Gold, style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Black)
            Text("Where great hair days begin", color = RoseGold)
        }
    }
}
