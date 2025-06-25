package org.kharis.todolist.state

import com.russhwolf.settings.Settings
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import org.kharis.todolist.model.Task

private const val TASKS_KEY = "tasks"

object TodoStatePersistence {
    private lateinit var settings: Settings

    private val json =
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

    fun init(context: Any) {
        settings = SettingsProvider.provide(context)
    }

    fun save(taskMap: Map<LocalDate, List<Task>>) {
        val serializableMap = taskMap.mapKeys { it.key.toString() }
        val encoded = json.encodeToString(serializableMap)
        settings.putString(TASKS_KEY, encoded)
    }

    fun load(): Map<LocalDate, List<Task>> {
        val encoded = settings.getStringOrNull(TASKS_KEY) ?: return emptyMap()

        return try {
            val restored = json.decodeFromString<Map<String, List<Task>>>(encoded)
            restored.mapKeys { LocalDate.parse(it.key) }
        } catch (e: Exception) {
            emptyMap()
        }
    }
}
