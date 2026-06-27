package com.trakky.app.hilt

import android.util.Log
import com.trakky.app.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@InstallIn(ActivityComponent::class)
@Module
class UserModule @Inject constructor() {

    @Provides
    @SQLQualifier
    fun provideSQLRepository(sqlRepository: SQLRepository): UserInterface{
        return sqlRepository
    }

    @Provides
    @FirebaseQualifier
    fun provideFirebaseRepository(firebaseRepository: FirebaseRepository) : UserInterface {
        return firebaseRepository
    }


}