package fitplan.ui.onboarding

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fitplan.core.firebase.LoginViewModel
import fitplan.core.firebase.data.Avatars
import fitplan.core.firebase.domain.avatarsList
import fitplan.core.firebase.domain.updateInfoToFirebase
import fitplan.planner.baseui.navigation.Screens
import fitplan.planner.baseui.utils.ProfileImage
import fitplan.planner.baseui.utils.dpFromPx
import fitplan.ui.theme.appGradient
import fitplan.ui.theme.backGround
import fitplan.ui.theme.buttonBackground
import fitplan.ui.theme.lightGray
import fitplan.ui.theme.textColor
import kotlinx.coroutines.launch

@Composable
fun AvatarsScreen(
    viewModel: LoginViewModel,
    navController: NavController,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var selectedAvatar by remember {
        mutableStateOf(Avatars(""))
    }
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val gender by viewModel.gender.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(backGround)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    )
    {
        LazyVerticalGrid(columns = GridCells.Adaptive(dpFromPx(context, 170f).dp)) {
            items(avatarsList) { avatar ->
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .height(dpFromPx(context, 220f).dp)
                        .width(dpFromPx(context, 220f).dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            selectedAvatar = avatar
                        }
                        .then(
                            if (selectedAvatar == avatar) Modifier
                                .border(
                                    color = textColor,
                                    shape = CircleShape,
                                    width = 1.dp
                                )
                            else Modifier
                        ),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(
                        if (selectedAvatar == avatar) textColor else lightGray
                    ),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(appGradient),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileImage(
                            imageUrl = avatar.imageUrl,
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape),
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (selectedAvatar.imageUrl != "") {
                            coroutineScope.launch {
                                println("Infooooooo: Name=$name email=$email gender=$gender")
                                updateInfoToFirebase(
                                    name = name,
                                    email = email,
                                    gender = gender,
                                    context = context,
                                    imageUrl = selectedAvatar.imageUrl
                                )
                                navController.navigate(
                                    Screens.Home.route
                                )
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please Select an Avatar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackground,
                    contentColor = textColor,
                    disabledContentColor = textColor,
                    disabledContainerColor = backGround,
                ),
                enabled = selectedAvatar.imageUrl != ""
            ) {
                Text(
                    text = "Take Me In",
                    color = textColor,
                    fontSize = 20.sp,
                )
            }
        }
    }
}