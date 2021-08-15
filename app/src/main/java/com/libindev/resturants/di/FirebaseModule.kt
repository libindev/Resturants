package com.libindev.resturants.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseService(
        @ApplicationContext context: Context
    ): FirebaseFirestore {
        FirebaseApp.initializeApp(context)
        return FirebaseFirestore.getInstance()
    }
}
