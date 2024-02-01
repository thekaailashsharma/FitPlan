package fitplan.planner.app.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fitplan.core.firebase.LoginViewModel
import fitplan.planner.baseui.navigation.Screens
import fitplan.preferences.datastore.UserDatastore
import fitplan.ui.onboarding.AvatarsScreen
import fitplan.ui.onboarding.OnBoardingScreen

@Composable
fun NavigationController(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current
    val datastore = UserDatastore(context)
    val name = datastore.getName.collectAsState(initial = "")
    val email = datastore.getEmail.collectAsState(initial = "")
    val pfp = datastore.getPfp.collectAsState(initial = "")
    val gender = datastore.getGender.collectAsState(initial = "")
    NavHost(
        navController = navController,
        startDestination = Screens.Onboarding.route
    ) {
        composable(Screens.Onboarding.route) {
            OnBoardingScreen(
                paddingValues = paddingValues,
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable(Screens.Home.route) {
            Column {
               Text(name.value, fontSize = 20.sp, color = Color.White)
                Text(email.value, fontSize = 20.sp, color = Color.White)
                Text(pfp.value, fontSize = 20.sp, color = Color.White)
                Text(gender.value, fontSize = 20.sp, color = Color.White)
            }
        }

        composable(Screens.ChooseAvatar.route) {
            AvatarsScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

    }
}