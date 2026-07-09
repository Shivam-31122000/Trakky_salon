package com.trakky.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import com.trakky.app.hilt.RoomDatabaseNamedAnnotation
import com.trakky.app.hilt.UserRepository
import com.trakky.app.nav.TrakkyApp
import com.trakky.app.ui.theme.TrakkyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RoomDatabaseNamedAnnotation
    @Inject
    lateinit var userRepository: UserRepository



    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)
        setContent { TrakkyTheme { TrakkyApp() } }

        userRepository.addUserData("Shivam",31)
    }
}
