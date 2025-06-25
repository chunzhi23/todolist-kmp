package org.kharis.todolist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import org.kharis.todolist.state.LocalTodoState
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.LocalAppColors
import org.kharis.todolist.ui.component.CircledIconButton

@Composable
fun TodoListView(date: LocalDate) {
    val todoState = LocalTodoState.current
    val colors = LocalAppColors.current
    val tasks = todoState.taskMap[date].orEmpty()

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        tasks.forEach { task ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = task.text,
                    fontSize = 16.sp,
                    color = colors.fg(AppColor.Surface),
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(start = 16.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                CircledIconButton(
                    icon = Icons.Rounded.Delete,
                    onClick = {
                        todoState.deleteTask(date, task)
                    },
                )
            }
        }
    }
}
