package fitplan.core.firebase.domain

import com.google.firebase.auth.FirebaseAuth
import fitplan.core.firebase.data.SignInStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


@ExperimentalCoroutinesApi
fun signInUser(email: String, password: String): Flow<SignInStatus> = callbackFlow {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign-in successful
                trySend(SignInStatus.Success).isSuccess
            } else {
                // Sign-in failed
                trySend(SignInStatus.Error(task.exception?.message ?: "Unknown error"))
                    .isSuccess
            }
        }

    awaitClose()
}