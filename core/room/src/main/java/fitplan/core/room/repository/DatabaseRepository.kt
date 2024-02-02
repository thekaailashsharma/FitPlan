package fitplan.core.room.repository


import fitplan.core.room.dao.TodosDao
import fitplan.core.room.entity.Todos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

class DatabaseRepo(private val todosDao: TodosDao) {
    val allTodos = todosDao.getTodos()

    fun insert(todo: Todos) {
        CoroutineScope(Dispatchers.IO).launch {
            todosDao.insert(todo)
        }
    }

    fun delete(id: UUID) {
        CoroutineScope(Dispatchers.IO).launch {
            todosDao.delete(id)
        }
    }
}