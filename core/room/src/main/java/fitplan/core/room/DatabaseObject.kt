package fitplan.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fitplan.core.room.dao.TodosDao
import fitplan.core.room.entity.Todos


@Database(entities = [Todos::class], version = 1)
abstract class DatabaseObject : RoomDatabase() {

    abstract fun todoDao(): TodosDao

    companion object {
        @Volatile
        private var Instance: DatabaseObject? = null

        fun getInstance(context: Context): DatabaseObject {
            synchronized(this) {
                if (Instance == null) {
                    Instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseObject::class.java,
                            "fitPlanDatabase"
                        ).build()
                }
            }
            return Instance!!
        }
    }
}