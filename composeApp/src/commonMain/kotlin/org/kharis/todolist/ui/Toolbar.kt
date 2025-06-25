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
import kotlinx.datetime.LocalDate
import org.kharis.todolist.state.LocalTodoState
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.LocalAppColors
import org.kharis.todolist.ui.component.RoundedRoleButton

@Composable
fun Toolbar(selectedDate: LocalDate) {
    val colors = LocalAppColors.current
    val todoState = LocalTodoState.current

    Row(modifier = Modifier.fillMaxWidth()) {
        /** UNDO */
        RoundedRoleButton(
            modifier = Modifier.weight(1f),
            colorRole = AppColor.SurfaceDim,
            onClick = { todoState.undo() },
        ) {
            Icon(
                Icons.AutoMirrored.Rounded.Undo,
                contentDescription = "Undo",
                tint = colors.fg(AppColor.SurfaceDim),
            )
        }
        Spacer(Modifier.width(8.dp))

        /** REMOVE ALL */
        RoundedRoleButton(
            modifier = Modifier.weight(1f),
            colorRole = AppColor.Error,
            onClick = { todoState.deleteAll(selectedDate) },
        ) {
            Text(
                text = "전체 삭제",
                color = colors.fg(AppColor.Error),
            )
        }
        Spacer(Modifier.width(8.dp))

        /** REDO */
        RoundedRoleButton(
            modifier = Modifier.weight(1f),
            colorRole = AppColor.SurfaceDim,
            onClick = { todoState.redo() },
        ) {
            Icon(
                Icons.AutoMirrored.Rounded.Redo,
                contentDescription = "Redo",
                tint = colors.fg(AppColor.SurfaceDim),
            )
        }
    }
}
