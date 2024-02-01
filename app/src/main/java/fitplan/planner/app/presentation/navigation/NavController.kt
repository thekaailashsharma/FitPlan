package fitplan.planner.app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fitplan.core.firebase.LoginViewModel
import fitplan.planner.baseui.navigation.Screens
import fitplan.ui.onboarding.AvatarsScreen
import fitplan.ui.onboarding.OnBoardingScreen

@Composable
fun NavigationController(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()

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

        }

        composable(Screens.ChooseAvatar.route) {
            AvatarsScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

    }
}