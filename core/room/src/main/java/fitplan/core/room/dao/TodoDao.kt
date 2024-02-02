package fitplan.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fitplan.core.room.entity.Todos
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TodosDao {
    @Query("SELECT * FROM todos")
    fun getTodos(): Flow<List<Todos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todos)

    @Query("DELETE FROM todos WHERE id = :id")
    suspend fun delete(id: UUID)
}