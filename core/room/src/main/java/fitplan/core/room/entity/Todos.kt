package fitplan.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            title, description, convertEpochDayToFormattedDate(date),
            priority.toString(),
            "${title.first()} ${tags}}",
        )

        return matchingCombinations.any {
            it?.contains(query, ignoreCase = true) ?: false
        }
    }
}

fun convertEpochDayToFormattedDate(epochDay: Long): String {
    val localDate = LocalDate.ofEpochDay(epochDay)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd") // Adjust the pattern as needed
    return localDate.format(formatter)
}