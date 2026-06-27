package com.trakky.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import com.trakky.app.hilt.FirebaseQualifier
import com.trakky.app.hilt.SQLQualifier
import com.trakky.app.hilt.UserInterface
import com.trakky.app.nav.TrakkyApp
import com.trakky.app.ui.theme.TrakkyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    @SQLQualifier
    lateinit var userInterface: UserInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)
        setContent { TrakkyTheme { TrakkyApp() } }

        userInterface.storeDataInDB("Shivam",25)
    }
}
