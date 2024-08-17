package com.example.androidtodoroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtodoroom.model.TodoDao
import com.example.androidtodoroom.model.TodoEntity


@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}