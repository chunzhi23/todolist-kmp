package org.kharis.todolist.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Redo
import androidx.compose.material.icons.automirrored.rounded.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.LocalAppColors
import org.kharis.todolist.ui.component.RoundedRoleButton

@Composable
fun Toolbar() {
    val colors = LocalAppColors.current

    Row(modifier = Modifier.fillMaxWidth()) {
        RoundedRoleButton(
            modifier = Modifier.weight(1f),
            colorRole = AppColor.SurfaceDim,
            onClick = { /* Undo */ },
        ) {
            Icon(
                Icons.AutoMirrored.Rounded.Undo,
                contentDescription = "Undo",
                tint = colors.fg(AppColor.SurfaceDim),
            )
        }

        Spacer(Modifier.width(8.dp))

        RoundedRoleButton(
            modifier = Modifier.weight(1f),
            colorRole = AppColor.Error,
            onClick = { /* 전체 삭제 */ },
        ) {
            Text(
                text = "전체 삭제",
                color = colors.fg(AppColor.Error),
            )
        }

        Spacer(Modifier.width(8.dp))

        RoundedRoleButton(
            modifier = Modifier.weight(1f),
            colorRole = AppColor.SurfaceDim,
            onClick = { /* Redo */ },
        ) {
            Icon(
                Icons.AutoMirrored.Rounded.Redo,
                contentDescription = "Redo",
                tint = colors.fg(AppColor.SurfaceDim),
            )
        }
    }
}
