package com.trakky.app.hilt

import android.util.Log
import javax.inject.Inject

class LoggerService @Inject constructor() {

    fun printLogOfStoreData(name: String){
        Log.wtf("Shivam Hilt",name)
    }

}