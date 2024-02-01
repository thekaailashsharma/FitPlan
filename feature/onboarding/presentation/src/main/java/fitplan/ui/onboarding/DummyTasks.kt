package fitplan.ui.onboarding

import androidx.compose.ui.graphics.Color
import fitplan.planner.data.dto.Tasks
import fitplan.ui.theme.blue
import fitplan.ui.theme.green
import fitplan.ui.theme.indigo
import fitplan.ui.theme.orange
import fitplan.ui.theme.yellow

val dummyTasks = listOf(
    Tasks(
        name = "Workout",
        color = orange,
        icon = R.drawable.workout
    ),
    Tasks(
        name = "Birthday",
        color = indigo,
        icon = R.drawable.birthday
    ),
    Tasks(
        name = "Shopping",
        color = yellow,
        icon = R.drawable.shopping
    ),
    Tasks(
        name = "Meeting",
        color = blue,
        icon = R.drawable.meeting
    ),
    Tasks(
        name = "Holiday",
        color = green,
        icon = R.drawable.holidays
    ),
    Tasks(
        name = "Todo List",
        color = orange,
        icon = R.drawable.todolist
    ),
    Tasks(
        name = "FitPlan",
        color = Color.White,
        icon = R.drawable.fitplan
    ),

    )