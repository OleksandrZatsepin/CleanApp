package dev.surehand.cleanapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.surehand.cleanapp.data.repository.UserRepositoryImpl
import dev.surehand.cleanapp.data.storage.SharedPrefUserStorage
import dev.surehand.cleanapp.data.storage.UserStorage
import dev.surehand.cleanapp.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage) : UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }

    @Provides
    @Singleton
    fun provideUserStorage(@ApplicationContext context: Context) : UserStorage {
        return SharedPrefUserStorage(context = context)
    }
}