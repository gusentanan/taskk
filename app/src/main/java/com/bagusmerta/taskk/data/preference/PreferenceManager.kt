package com.bagusmerta.taskk.data.preference


import androidx.datastore.core.DataStore
import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import com.bagusmerta.taskk.data.preference.themes.toTheme
import com.bagusmerta.taskk.data.preference.themes.toThemePref
import com.bagusmerta.taskk.model.preference.ThemePreference
import com.bagusmerta.taskk.utils.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class PreferenceManager @Inject constructor(
    @Named(Dispatcher.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val taskkThemeDataStore: DataStore<ThemePreference>
) {

    fun getTheme(): Flow<TaskkTheme> {
        return taskkThemeDataStore.data.map { it.toTheme() }
            .catch { emit(TaskkTheme.SYSTEM) }
            .flowOn(dispatcher)
    }

    suspend fun setTheme(data: TaskkTheme) {
        withContext(dispatcher){
            taskkThemeDataStore.updateData {
                data.toThemePref()
            }
        }
    }
}