package com.example.androidtodoroom.di

import android.content.Context
import androidx.room.Room
import com.example.androidtodoroom.database.AppDatabase
import com.example.androidtodoroom.repositories.TodoRepository
import com.example.androidtodoroom.repositories.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "tododb.db"
        ).build()
    }
}


@InstallIn(SingletonComponent::class)
@Module
abstract class TodoRepositoryModule {
    @Binds
    abstract fun bindsTodoRepository(todoImpl: TodoRepositoryImpl): TodoRepository
}