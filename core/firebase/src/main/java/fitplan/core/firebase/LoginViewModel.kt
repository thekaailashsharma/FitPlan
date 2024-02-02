package fitplan.core.firebase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fitplan.core.firebase.data.Avatars
import fitplan.core.firebase.data.ProfileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private val _data = MutableStateFlow<ProfileInfo?>(null)
    val data: StateFlow<ProfileInfo?> = _data.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _avatar = MutableStateFlow<Avatars?>(null)
    val avatar: StateFlow<Avatars?> = _avatar.asStateFlow()

    private val _gender = MutableStateFlow("")
    val gender = _gender.asStateFlow()


    fun setData(email: String, name: String, gender: String) {
        _email.value = email
        _name.value = name
        _gender.value = gender
    }

}