package com.trakky.app.hilt

import javax.inject.Inject

class FirebaseDBRepository @Inject constructor(val loggerService: LoggerService) : UserRepository {


    override fun addUserData(name: String, age: Int) {
        loggerService.printLogOfStoreData("Data Saved in Firebase.")
    }

}