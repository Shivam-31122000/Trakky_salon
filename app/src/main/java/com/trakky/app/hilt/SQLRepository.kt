package com.trakky.app.hilt

import android.util.Log
import javax.inject.Inject


class SQLRepository @Inject constructor(val loggerService: LoggerService) : UserInterface {

    override fun storeDataInDB(name: String, age: Int) {
        loggerService.logService("data stored in DB")
    }
}