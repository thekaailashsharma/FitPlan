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
import fitplan.core.room.HomeViewModel
import fitplan.planner.baseui.navigation.Screens
import fitplan.preferences.datastore.UserDatastore
import fitplan.ui.newTask.NewTaskScreen
import fitplan.ui.onboarding.AvatarsScreen
import fitplan.ui.onboarding.OnBoardingScreen
import fitplan.ui.presentation.HomeScreen
import fitplan.ui.presentation.NewProfileScreen
import fitplan.ui.presentation.SplashScreen

@Composable
fun NavigationController(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val datastore = UserDatastore(context)
    val email = datastore.getEmail.collectAsState(initial = "")
    val isLoggedIn = datastore.getLoginStatus.collectAsState(initial = false)
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.Onboarding.route) {
            OnBoardingScreen(
                paddingValues = paddingValues,
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel,
               email = email.value
            )
        }

        composable(Screens.ChooseAvatar.route) {
            AvatarsScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable(Screens.AddTask.route) {
            NewTaskScreen(homeViewModel, navController)
        }

        composable(Screens.ProfileScreen.route) {
            NewProfileScreen(navController = navController, homeViewModel)
        }

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

    }
}