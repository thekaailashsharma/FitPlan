package fitplan.ui.newTask

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import fitplan.core.room.HomeViewModel
import fitplan.core.room.entity.Todos
import fitplan.planner.baseui.navigation.Screens
import fitplan.planner.data.dto.Tasks
import fitplan.ui.theme.backGround
import fitplan.ui.theme.lightGray
import fitplan.ui.theme.merinda
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.textColor
import fitplan.ui.theme.yellow
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun NewTaskScreen(homeViewModel: HomeViewModel, navController: NavController) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val priorityState = rememberFWheelPickerState()
    val state = rememberCollapsingToolbarScaffoldState()
    val priority = remember { mutableIntStateOf(0) }
    val isPriorityVisible = remember { mutableStateOf(false) }
    val task = remember { mutableStateOf<Tasks?>(null) }
    val isTaskVisible = remember { mutableStateOf(false) }
    val timeDialogState = rememberMaterialDialogState()
    var timeSelected by remember {
        mutableStateOf(false)
    }
    var date by remember {
        mutableStateOf(LocalDate.now())
    }
    val isDateVisible = remember { mutableStateOf(false) }

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backGround)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.Home.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, lightGray.copy(alpha = 0.7f)),
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = monteEB,
                        color = textColor
                    )
                }

                Button(
                    onClick = {
                        homeViewModel.insertTodo(
                            Todos(
                                title = title.text,
                                description = description.text,
                                priority = priority.intValue,
                                date = date.toEpochDay(),
                                completed = false,
                                tags = task.value?.name,
                                location = "Mumbai"
                            )
                        )
                        navController.navigate(Screens.Home.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, lightGray.copy(alpha = 0.7f)),
                ) {
                    Text(
                        text = "Done",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = monteEB,
                        color = textColor
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backGround)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "New Task",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = monteEB,
                    color = textColor,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                NewTaskTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = "Title",
                    placeholder = "Give your task a title"
                )

                Spacer(modifier = Modifier.height(10.dp))

                NewTaskTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    label = "Description",
                    placeholder = "Describe what's your goal ?"
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp, top = 160.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(20.dp))
                    ReusableEditTextCard(
                        text = if (priority.intValue == 0) "Set a priority" else "Priority - ${priorityState.currentIndex}",
                        icon = R.drawable.priority,
                        isSelected = priority.intValue != 0
                    ) {
                        isPriorityVisible.value = !isPriorityVisible.value
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    (if (task.value != null) task.value?.name else "Add suitable tags")?.let {
                        ReusableEditTextCard(
                            text = it,
                            icon = if (task.value != null) task.value?.icon
                                ?: R.drawable.tags else R.drawable.tags,
                            isSelected = task.value != null
                        ) {
                            isTaskVisible.value = !isTaskVisible.value
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    ReusableEditTextCard(
                        text = if (!timeSelected) "Set a due date"
                        else "Due on ${date.dayOfMonth}-${date.monthValue}-${date.year}",
                        icon = R.drawable.dt,
                        isSelected = timeSelected
                    ) {
                        timeDialogState.show()
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    ReusableEditTextCard(text = "Add a location", icon = R.drawable.loc) {

                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }

            }

            if (isPriorityVisible.value) {
                PriorityDialog(
                    currentPriority = priority,
                    state = priorityState,
                    isVisible = isPriorityVisible.value,
                    title = "Set a priority",
                    successRequest = {
                        isPriorityVisible.value = false
                    },
                    dismissRequest = {
                        isPriorityVisible.value = false
                    }
                )
            }

            if (isTaskVisible.value) {
                TaskDialog(
                    isVisible = isTaskVisible.value,
                    title = "Add suitable tags",
                    successRequest = {
                        isTaskVisible.value = false
                    },
                    dismissRequest = {
                        isTaskVisible.value = false
                    },
                    currentTask = task
                )
            }

            MaterialDialog(
                dialogState = timeDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        timeSelected = true
                        Toast.makeText(
                            context,
                            "Depart Time selected",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    negativeButton(text = "Cancel") {
                        timeSelected = false
                    }
                }
            ) {
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "Pick a date",
                ) {
                    date = it
                }
            }

        }
    }
}


