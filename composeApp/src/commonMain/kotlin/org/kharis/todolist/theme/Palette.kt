package org.kharis.todolist.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

data class ThemeColor(
    val light: Color,
    val dark: Color,
    val onLight: Color,
    val onDark: Color,
)

enum class AppColor(
    val tc: ThemeColor,
) {
    Primary(
        ThemeColor(
            light = Color(0xFF2196F3),
            dark = Color(0xFF0D47A1),
            onLight = Color(0xFF000000),
            onDark = Color(0xFFFFFFFF),
        ),
    ),
    Background(
        ThemeColor(
            light = Color(0xFFF5F7FA),
            dark = Color(0xFF1A1A1A),
            onLight = Color(0xFF000000),
            onDark = Color(0xFFFFFFFF),
        ),
    ),
    Surface(
        ThemeColor(
            light = Color(0xFFFFFFFF),
            dark = Color(0xFF2D2D2D),
            onLight = Color(0xFF000000),
            onDark = Color(0xFFFFFFFF),
        ),
    ),
    SurfaceDim(
        ThemeColor(
            light = Color(0xFFF5F5F5),
            dark = Color(0xFF3D3D3D),
            onLight = Color(0xFF212121),
            onDark = Color(0xFFFFFFFF),
        ),
    ),
    Error(
        ThemeColor(
            light = Color(0xFFAA3230),
            dark = Color(0xFFBA393A),
            onLight = Color(0xFFFFFFFF),
            onDark = Color(0xFFFFFFFF),
        ),
    ),
    ;

    companion object {
        fun resolveAll(darkTheme: Boolean): AppColors {
            val bg = entries.associateWith { if (darkTheme) it.tc.dark else it.tc.light }
            val fg = entries.associateWith { if (darkTheme) it.tc.onDark else it.tc.onLight }
            return AppColors(bg, fg)
        }
    }
}

@Composable
@Preview
fun ThemeColorsPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        AppColor.entries.forEach { color ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color.tc.light)
                            .padding(8.dp),
                ) {
                    Text(
                        text = color.name,
                        color = color.tc.onLight,
                    )
                }
                Row(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color.tc.dark)
                            .padding(8.dp),
                ) {
                    Text(
                        text = color.name,
                        color = color.tc.onDark,
                    )
                }
            }
        }
    }
}
