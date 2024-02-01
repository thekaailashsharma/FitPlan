package fitplan.ui.login.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fitplan.core.firebase.LoginViewModel
import fitplan.core.firebase.data.SignInStatus
import fitplan.core.firebase.domain.createUser
import fitplan.planner.baseui.navigation.Screens
import fitplan.planner.baseui.utils.TextFieldWithIcons
import fitplan.ui.theme.R
import fitplan.ui.theme.backGround
import fitplan.ui.theme.monte
import fitplan.ui.theme.monteEB
import fitplan.ui.theme.textColor
import fitplan.ui.theme.yellow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RegisterScreen(
    isRegistered: MutableState<Boolean>,
    navController: NavController,
    viewModel: LoginViewModel
) {
    var registerEmail by remember { mutableStateOf(TextFieldValue("")) }
    var registerPassword by remember { mutableStateOf(TextFieldValue("")) }
    var registerName by remember { mutableStateOf(TextFieldValue("")) }
    var registerGender by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    AnimatedVisibility(
        visible = isRegistered.value,
        enter = slideInHorizontally(initialOffsetX = {
            -it
        }, animationSpec =
        tween(durationMillis = 500)),
        exit = slideOutHorizontally(
            targetOffsetX = {
                -it
            }, animationSpec =
            tween(durationMillis = 500)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = backGround.copy(alpha = 0.7f),
                ),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, textColor)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.fitplan),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TextFieldWithIcons(
                        textValue = "Name",
                        placeholder = "Enter your Name",
                        icon = Icons.Filled.Person,
                        mutableText = registerName,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        isTrailingVisible = false,
                        trailingIcon = null,
                        onTrailingClick = {},
                        isEnabled = true,
                        onValueChanged = { value ->
                            registerName = value
                        },
                        containerColor = Color.Transparent,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithIcons(
                        textValue = "Gender",
                        placeholder = "Enter your Gender",
                        icon = Icons.Filled.Male,
                        mutableText = registerGender,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        isTrailingVisible = false,
                        trailingIcon = null,
                        onTrailingClick = {},
                        isEnabled = true,
                        onValueChanged = { value ->
                            registerGender = value
                        },
                        containerColor = Color.Transparent,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithIcons(
                        textValue = "Email",
                        placeholder = "Enter your email",
                        icon = Icons.Filled.Wallet,
                        mutableText = registerEmail,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        isTrailingVisible = false,
                        trailingIcon = null,
                        onTrailingClick = {},
                        isEnabled = true,
                        onValueChanged = { value ->
                            registerEmail = value
                        },
                        containerColor = Color.Transparent,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithIcons(
                        textValue = "Password",
                        placeholder = "Enter your password",
                        icon = Icons.Filled.Wallet,
                        mutableText = registerPassword,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        isTrailingVisible = true,
                        trailingIcon = null,
                        onTrailingClick = {},
                        isEnabled = true,
                        onValueChanged = { value ->
                            registerPassword = value
                        },
                        containerColor = Color.Transparent,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                createUser(registerEmail.text, registerPassword.text)
                                    .collectLatest { status ->
                                        when (status) {
                                            is SignInStatus.Success -> {
                                                Toast.makeText(
                                                    context,
                                                    "Success",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                viewModel.setData(
                                                    name = registerName.text,
                                                    email = registerEmail.text,
                                                    gender = registerGender.text,
                                                )
                                                navController.navigate(Screens.ChooseAvatar.route)
                                            }

                                            is SignInStatus.Error -> {
                                                Toast.makeText(
                                                    context,
                                                    status.errorMessage,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        border = BorderStroke(1.dp, yellow.copy(alpha = 0.7f)),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = "Register",
                            fontSize = 19.sp,
                            fontFamily = monteEB,
                            fontWeight = FontWeight.ExtraBold,
                            softWrap = true,
                            modifier = Modifier.fillMaxWidth(0.75f),
                            textAlign = TextAlign.Center,
                            color = textColor.copy(0.85f),
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Already a member? Sign in",
                        fontSize = 16.sp,
                        fontFamily = monte,
                        fontWeight = FontWeight.ExtraBold,
                        softWrap = true,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                isRegistered.value = !isRegistered.value
                            },
                        textAlign = TextAlign.Center,
                        color = textColor.copy(0.85f),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
    AnimatedVisibility(
        visible = !isRegistered.value,
        enter = slideInHorizontally(
            initialOffsetX = {
                it
            }, animationSpec =
            tween(durationMillis = 500)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = {
                it
            }, animationSpec =
            tween(durationMillis = 500)
        )
    ) {
        SignInScreen(
            isRegistered = isRegistered,
            navController = navController,
            viewModel = viewModel
        )
    }
}
