package fitplan.core.firebase.domain

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import fitplan.core.firebase.data.ProfileInfo

fun updateInfoToFirebase(
    context: Context,
    name: String?,
    email: String,
    gender: String?,
    imageUrl: String?
) {
    val profile = ProfileInfo(
        name,
        email,
        gender,
        imageUrl
    )

    val db = FirebaseFirestore.getInstance()
    email.let {
        db.collection("ProfileInfo").document(it).set(profile)
            .addOnSuccessListener {

                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { exception ->
                Toast.makeText(
                    context,
                    "Process Failed : " + exception.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

}