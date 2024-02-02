package fitplan.core.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fitplan.core.room.entity.Todos
import fitplan.core.room.repository.DatabaseRepo
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val dbRepository: DatabaseRepo,
) : AndroidViewModel(application) {

    val allTodos = dbRepository.allTodos

    fun insertTodo(todos: Todos) {
        viewModelScope.launch {
            dbRepository.insert(todos)
        }
    }

    fun deleteTodo(uuid: UUID) {
        viewModelScope.launch {
            dbRepository.delete(uuid)
        }
    }
}