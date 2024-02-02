package fitplan.ui.presentation

import fitplan.planner.baseui.navigation.Screens
import fitplan.ui.theme.appGradient
import fitplan.ui.theme.textColor
import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fitplan.core.room.HomeViewModel
import fitplan.planner.baseui.utils.ProfileImage
import fitplan.preferences.datastore.UserDatastore
import fitplan.ui.theme.backGround
import fitplan.ui.theme.buttonBackground
import fitplan.ui.theme.indigo
import fitplan.ui.theme.monteEB
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalComposeUiApi::class
)
@Composable
fun NewProfileScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    val context = LocalContext.current
    val dataStore = UserDatastore(context)
    val name by homeViewModel.name.collectAsState("")
    val pfp by homeViewModel.pfp.collectAsState("")
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, bottom = 7.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ProfileImage(
                imageUrl = pfp,
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = indigo,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = name ?: "User Name",
                color = textColor,
                fontSize = 20.sp,
                fontFamily = monteEB,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Keep Liking",
                color = textColor,
                fontSize = 12.sp,
                fontFamily = monteEB,
                softWrap = true,
                modifier = Modifier.padding(end = 7.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = buttonBackground.copy(0.8f)
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column {

                RepeatedProfileInfo(
                    icon = Icons.Filled.EditNote,
                    text = "Edit Profile"
                )
                RepeatedProfileInfo(
                    icon = Icons.Filled.Notifications,
                    text = "Notifications"
                )
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = buttonBackground.copy(0.8f)
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column {

                RepeatedProfileInfo(
                    icon = Icons.Filled.SupportAgent,
                    text = "Help and Support"
                )
                RepeatedProfileInfo(
                    icon = Icons.Filled.ContactPage,
                    text = "Contact Us"
                )
                RepeatedProfileInfo(
                    icon = Icons.Filled.PrivacyTip,
                    text = "Privacy Policy"
                )
            }

        }

        Button(
            onClick = {
                coroutineScope.launch {
                    navController.navigate(Screens.Onboarding.route)
                    dataStore.saveName("")
                    dataStore.savePfp("")
                    dataStore.saveName("")
                    dataStore.saveGender("")
                    dataStore.saveLoginStatus(false)
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFD5065),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(35.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
        ) {
            Text(
                text = "Logout",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = monteEB,
                modifier = Modifier.padding(bottom = 4.dp),
                maxLines = 1,
                softWrap = true
            )
        }

    }
}



@Composable
fun RepeatedProfileInfo(
    icon: ImageVector,
    text: String,
    onClick: (() -> Unit?)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick?.invoke()
            }
            .padding(start = 10.dp, top = 15.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = textColor,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 13.dp)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 15.sp,
            fontFamily = monteEB
        )
    }
}