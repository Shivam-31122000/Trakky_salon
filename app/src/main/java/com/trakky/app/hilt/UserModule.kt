package com.trakky.app.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named


@InstallIn(ActivityComponent::class)
@Module
class UserModule {



    @Provides
    @RoomDatabaseNamedAnnotation
    fun saveInfoInLocalDB(roomDBRepository: RoomDBRepository) : UserRepository{
        return roomDBRepository
    }


    @Provides
    @FirebaseNamedAnnotation
    fun saveInfoInFirebaseDB(firebaseDBRepository: FirebaseDBRepository) : UserRepository{
        return firebaseDBRepository
    }

}