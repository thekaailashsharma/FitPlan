package fitplan.planner.baseui.module


import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fitplan.core.room.DatabaseObject
import fitplan.core.room.repository.DatabaseRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabaseRepo(@ApplicationContext context: Context): DatabaseRepo {
        val dB = DatabaseObject.getInstance(context)
        return DatabaseRepo(dB.todoDao())
    }
}