package com.trakky.app.hilt

import android.util.Log
import javax.inject.Inject

class LoggerService @Inject constructor() {

    fun logService(name: String){
        Log.d("HiltData", "storeDataInDB: $name")
    }
}