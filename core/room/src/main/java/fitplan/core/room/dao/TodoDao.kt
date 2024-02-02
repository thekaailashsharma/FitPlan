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

    @Query(
        """
           SELECT * from todos as his 
           where his.completed like '%' || :query || '%' 
           or his.date like '%' || :query || '%' 
           or his.title like '%' || :query || '%' 
           or his.description like '%' || :query || '%' 
           or his.priority like '%' || :query || '%' 
           or his.tags like '%' || :query || '%' 
       """
    )
    fun search(query: String): Flow<List<Todos>>
}