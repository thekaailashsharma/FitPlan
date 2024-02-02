package fitplan.planner.baseui.navigation

sealed class Screens(val route: String) {
    object Onboarding : Screens("onboarding")
    object Home : Screens("home")
    object ChooseAvatar : Screens("choose_avatar")
    object AddTask : Screens("add_task")
    object SplashScreen : Screens("splash_screen")
    object SearchScreen: Screens("search-screen")
}