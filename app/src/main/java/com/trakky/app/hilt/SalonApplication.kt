package com.trakky.app.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton


@HiltAndroidApp
@Singleton
class SalonApplication : Application()



