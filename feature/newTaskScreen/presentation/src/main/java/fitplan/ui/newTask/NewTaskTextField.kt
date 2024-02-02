package fitplan.ui.newTask

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import fitplan.ui.theme.backGround
import fitplan.ui.theme.merinda
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.textColor
import fitplan.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    placeholder: String = "",
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = merinda,
                color = textColor
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = merinda,
                color = yellow
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontFamily = monteEB,
            color = textColor
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = textColor,
            containerColor = backGround,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = textColor,
            placeholderColor = yellow,
        ),
    )
}