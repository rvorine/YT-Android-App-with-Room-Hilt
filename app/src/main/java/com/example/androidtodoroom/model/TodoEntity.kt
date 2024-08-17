package com.example.androidtodoroom.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val title:String,
    val isComplete:Boolean = false
)


@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items:List<TodoEntity>)

    @Query("SELECT * FROM TodoEntity")
    fun getAllTodo():Flow<List<TodoEntity>>

    @Update
    suspend fun updateTodo(item:TodoEntity)

    @Delete
    suspend fun deleteTodo(item: TodoEntity)
}
