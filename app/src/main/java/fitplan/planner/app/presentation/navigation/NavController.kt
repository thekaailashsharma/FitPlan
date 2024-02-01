package fitplan.planner.app.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fitplan.ui.onboarding.OnBoardingScreen

@Composable
fun NavigationController(paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Onboarding.route
    ) {
        composable(Screens.Onboarding.route) {
            OnBoardingScreen(paddingValues = paddingValues)
        }

        composable(Screens.Home.route) {

        }

    }
}