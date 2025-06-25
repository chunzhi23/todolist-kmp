package org.kharis.todolist.state

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object SettingsProvider {
    actual fun provide(context: Any): Settings {
        val ctx = context as Context
        val sharedPrefs = ctx.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPrefs)
    }
}
