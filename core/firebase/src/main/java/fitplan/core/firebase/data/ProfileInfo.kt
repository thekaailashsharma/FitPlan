package fitplan.core.firebase.data

data class ProfileInfo(
    val name: String?,
    val email: String,
    val gender: String?,
    val imageUrl: String?,
) {
    constructor() : this("", "", "", "")
}