package fitplan.core.firebase.data

sealed class SignInStatus {
    object Success : SignInStatus()
    data class Error(val errorMessage: String) : SignInStatus()
}
