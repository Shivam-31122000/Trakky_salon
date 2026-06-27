package com.trakky.app.hilt

import dagger.Provides


interface UserInterface {
   fun storeDataInDB(name: String, age: Int)
}