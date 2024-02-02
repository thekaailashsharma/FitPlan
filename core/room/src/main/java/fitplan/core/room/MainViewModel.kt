package fitplan.core.room

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fitplan.core.room.entity.Todos
import fitplan.core.room.repository.DatabaseRepo
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val dbRepository: DatabaseRepo,
) : AndroidViewModel(application) {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val allTodos = dbRepository.allTodos

    private val _todos = MutableStateFlow<List<Todos>>(emptyList())
    val todos: StateFlow<List<Todos>> = _todos

    private val _isChecking = MutableStateFlow<Boolean>(false)
    val isChecking: StateFlow<Boolean> = _isChecking.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _pfp = MutableStateFlow("")
    val pfp = _pfp.asStateFlow()

    fun updateUser(newName: String, newPfp: String) {
       _name.value = newName
       _pfp.value = newPfp
    }


    fun updateTodos(newTodos: List<Todos>) {
        _todos.value = newTodos
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchText.value = newQuery
    }


    @OptIn(FlowPreview::class)
    fun searchTodos(searchText: String) {
        viewModelScope.launch {
            flow {
                emit(searchText)
            }
                .debounce(300)
                .onEach { _isSearching.emit(true) }
                .combine(_todos) { text, todos ->
                    if (text.isBlank()) {
                        todos
                    } else {
                        todos.filter {
                            it.title.contains(text, ignoreCase = true) || it.description?.contains(
                                text,
                                ignoreCase = true
                            ) == true
                        }
                    }
                }
                .onEach { _isSearching.emit(false) }
                .collect { filteredTodos ->
                    _todos.emit(filteredTodos)
                }
        }
    }


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

    fun searchQuery(searchQuery: String) = dbRepository.searchQuery(searchQuery)

}