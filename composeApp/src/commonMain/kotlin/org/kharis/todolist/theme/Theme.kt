package org.kharis.todolist.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppColors =
    staticCompositionLocalOf<AppColors> {
        error("No AppColors provided")
    }

@Composable
fun CustomMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = AppColor.resolveAll(darkTheme)

    CompositionLocalProvider(LocalAppColors provides colors) {
        MaterialTheme(content = content)
    }
}

class AppColors internal constructor(
    private val bgMap: Map<AppColor, Color>,
    private val fgMap: Map<AppColor, Color>,
) {
    fun bg(role: AppColor): Color = bgMap.getValue(role)

    fun fg(role: AppColor): Color = fgMap.getValue(role)
}
