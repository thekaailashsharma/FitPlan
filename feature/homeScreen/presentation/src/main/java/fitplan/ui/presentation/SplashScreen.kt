package fitplan.ui.presentation


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fitplan.planner.baseui.navigation.Screens
import fitplan.preferences.datastore.UserDatastore
import fitplan.ui.theme.backGround
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    val context = LocalContext.current
    val datastore = UserDatastore(context)
    val isLoggedIn = datastore.getLoginStatus.collectAsState(initial = false)
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        println("User is $user")
        navController.navigate(if (isLoggedIn.value) Screens.Home.route else Screens.Onboarding.route)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
    ) {
        Image(
            painter = painterResource(id = fitplan.ui.theme.R.drawable.fitplan),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}