package com.trakky.app.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@Singleton
@HiltAndroidApp
class SalonApplication : Application() {

}