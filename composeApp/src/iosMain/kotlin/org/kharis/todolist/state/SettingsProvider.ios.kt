package org.kharis.todolist.state

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object SettingsProvider {
    actual fun provide(context: Any): Settings = AppleSettings(NSUserDefaults.standardUserDefaults)
}
