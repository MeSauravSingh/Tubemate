package com.example.tubemate.di

import android.content.Context
import androidx.room.Room
import com.example.tubemate.network.RemoteDataSource
import com.example.tubemate.network.UserApi
import com.example.tubemate.room.ProductDao
import com.example.tubemate.room.LocalDatabase
import com.example.tubemate.room.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi(
        remoteDataSource: RemoteDataSource
    ): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context
    ): LocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "LocalDB",
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(
        localDataSource: LocalDatabase
    ): RecipeDao {
        return localDataSource.recipeDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(
        localDataSource: LocalDatabase
    ): ProductDao {
        return localDataSource.productDao()
    }

}