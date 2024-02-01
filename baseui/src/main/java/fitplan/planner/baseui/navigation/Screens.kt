package fitplan.planner.baseui.navigation

sealed class Screens(val route: String) {
    object Onboarding : Screens("onboarding")
    object Home : Screens("home")
    object ChooseAvatar : Screens("choose_avatar")
    object Calendar : Screens("calendar")
    object AddEditTask : Screens("add_edit_task")
    object SignIn : Screens("sign_in")
    object SplashScreen : Screens("splash_screen")
}