package org.kharis.todolist.state

import com.russhwolf.settings.Settings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object SettingsProvider {
    fun provide(context: Any): Settings
}
