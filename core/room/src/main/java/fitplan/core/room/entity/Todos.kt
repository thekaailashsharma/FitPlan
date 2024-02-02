package fitplan.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todos")
data class Todos(
    val completed: Boolean,
    @PrimaryKey(autoGenerate = false)
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String? = null,
    val date: Long,
    val priority: Int = 0,
    val tags: String? = null,
    val location: String? = null
)