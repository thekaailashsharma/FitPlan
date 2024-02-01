package fitplan.planner.baseui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import fitplan.ui.theme.buttonBackground
import fitplan.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcons(
    modifier: Modifier = Modifier,
    textValue: String,
    placeholder: String,
    icon: ImageVector,
    mutableText: TextFieldValue,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    isTrailingVisible: Boolean = false,
    trailingIcon: ImageVector? = null,
    onTrailingClick: () -> Unit = {},
    isEnabled: Boolean = true,
    onValueChanged: (TextFieldValue) -> Unit,
    onSearch: () -> Unit = {},
    contentColor: Color = textColor,
    containerColor: Color = buttonBackground,
) {
    val isPassword = remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }
    val isPasswordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = mutableText,
        leadingIcon = {
            Icon(
                imageVector = icon,
                tint = textColor,
                contentDescription = "Icon"
            )
        },
        trailingIcon = {
            if (isPassword.value) {
                if (isPasswordVisible.value) {
                    IconButton(onClick = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            tint = textColor,
                            contentDescription = "Icon"
                        )
                    }
                } else {
                    IconButton(onClick = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            tint = textColor,
                            contentDescription = "Icon"
                        )
                    }
                }
            } else {
                if (isTrailingVisible && trailingIcon != null) {
                    IconButton(onClick = {
                        onTrailingClick()
                    }) {
                        Icon(
                            imageVector = trailingIcon,
                            tint = textColor,
                            contentDescription = "Icon"
                        )
                    }
                }
            }
        },
        onValueChange = onValueChanged,
        label = { Text(text = textValue, color = textColor) },
        placeholder = { Text(text = placeholder, color = textColor) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        modifier = modifier
            .padding(start = 15.dp, top = 5.dp, bottom = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = contentColor,
            placeholderColor = contentColor,
            focusedBorderColor = contentColor,
            disabledBorderColor = contentColor,
            disabledTextColor = contentColor,
            cursorColor = contentColor,
            errorCursorColor = contentColor,
            errorLeadingIconColor = contentColor,
            errorBorderColor = contentColor,
            focusedLabelColor = contentColor,
            disabledLabelColor = contentColor,
            errorLabelColor = contentColor,
            containerColor = containerColor,
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(20.dp),
        visualTransformation = if (!isPasswordVisible.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
    )
}