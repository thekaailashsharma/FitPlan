package fitplan.ui.newTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.FWheelPickerFocusVertical
import com.sd.lib.compose.wheel_picker.FWheelPickerState
import fitplan.planner.baseui.utils.ReusableDialog
import fitplan.planner.data.dto.Tasks
import fitplan.ui.onboarding.MarqueeCard
import fitplan.ui.onboarding.dummyTasks
import fitplan.ui.theme.indigo

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskDialog(
    currentTask: MutableState<Tasks?>,
    isVisible: Boolean,
    title: String,
    successRequest: () -> Unit,
    dismissRequest: () -> Unit
) {
    ReusableDialog(
        isVisible = isVisible,
        title = title,
        successRequest = {
            successRequest()
        },
        dismissRequest = {
            dismissRequest()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 3
            ) {
                dummyTasks.forEach { task ->
                    MarqueeCard(taskItem = task, isSelected = currentTask.value == task) {
                        currentTask.value = task
                    }
                }
            }

        }
    }
}
