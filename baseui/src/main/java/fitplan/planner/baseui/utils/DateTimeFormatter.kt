package fitplan.planner.baseui.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun convertEpochDayToFormattedDate(epochDay: Long): String {
    val localDate = LocalDate.ofEpochDay(epochDay)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd") // Adjust the pattern as needed
    return localDate.format(formatter)
}