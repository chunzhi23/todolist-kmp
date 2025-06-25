package org.kharis.todolist.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.LocalAppColors

@Composable
fun CircledIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    colorRole: AppColor = AppColor.SurfaceDim,
    contentDescription: String? = null,
) {
    val colors = LocalAppColors.current

    Box(
        modifier =
            Modifier
                .clip(CircleShape)
                .size(40.dp)
                .background(colors.bg(colorRole))
                .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            icon,
            contentDescription,
            tint = colors.fg(colorRole),
        )
    }
}
