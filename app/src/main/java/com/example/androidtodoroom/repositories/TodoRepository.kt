package com.example.androidtodoroom.repositories

import com.example.androidtodoroom.database.AppDatabase
import com.example.androidtodoroom.model.TodoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TodoRepository {
    suspend fun insert(items: List<TodoEntity>)

    fun getAllTodo(): Flow<List<TodoEntity>>

    suspend fun update(item: TodoEntity)

    suspend fun delete(item: TodoEntity)
}


class TodoRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : TodoRepository {
    override suspend fun insert(items: List<TodoEntity>) = db.todoDao().insert(items)

    override fun getAllTodo(): Flow<List<TodoEntity>> = db.todoDao().getAllTodo()

    override suspend fun update(item: TodoEntity) = db.todoDao().updateTodo(item)

    override suspend fun delete(item: TodoEntity) = db.todoDao().deleteTodo(item)

}