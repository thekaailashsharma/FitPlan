package fitplan.planner.baseui.navigation

sealed class Screens(val route: String) {
    object Onboarding : Screens("onboarding")
    object Home : Screens("home")
    object ChooseAvatar : Screens("choose_avatar")
    object AddTask : Screens("add_task")
    object ProfileScreen: Screens("profile_screen")
    object SplashScreen: Screens("splash_screen")
}