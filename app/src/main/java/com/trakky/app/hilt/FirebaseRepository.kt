package com.trakky.app.hilt

import android.util.Log
import dagger.Binds
import javax.inject.Inject


class FirebaseRepository @Inject constructor(val loggerService: LoggerService) : UserInterface {

    override fun storeDataInDB(name: String, age: Int) {
        loggerService.logService("data stored in Firebase")
    }


}