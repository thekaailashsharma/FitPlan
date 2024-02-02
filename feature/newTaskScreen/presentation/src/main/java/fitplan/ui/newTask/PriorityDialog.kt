package fitplan.ui.newTask

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.FWheelPickerFocusVertical
import com.sd.lib.compose.wheel_picker.FWheelPickerState
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import fitplan.planner.baseui.utils.ReusableDialog
import fitplan.ui.theme.indigo
import fitplan.ui.theme.merinda
import fitplan.ui.theme.yellow

@Composable
fun PriorityDialog(
    currentPriority: MutableState<Int>,
    state: FWheelPickerState,
    isVisible: Boolean,
    title: String,
    successRequest: () -> Unit,
    dismissRequest: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    ReusableDialog(
        isVisible = isVisible,
        title = title,
        successRequest = {
            if (currentPriority.value != 0) {
                successRequest()
            } else {
               Toast.makeText(
                   context,
                   "Priority must be greater than 0",
                   Toast.LENGTH_SHORT
               ).show()
            }
        },
        dismissRequest = {
            dismissRequest()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            FVerticalWheelPicker(
                focus = {
                    FWheelPickerFocusVertical(dividerColor = indigo, dividerSize = 2.dp)
                },
                count = 5,
                state = state
            ) {
                currentPriority.value = it
                Text(
                    text = it.toString(),
                    textAlign = TextAlign.Center,
                    color = yellow,
                    fontFamily = merinda,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 0.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.priority),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}