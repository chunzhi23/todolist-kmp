package org.kharis.todolist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import org.kharis.todolist.model.Task
import org.kharis.todolist.state.LocalTodoState
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.LocalAppColors
import org.kharis.todolist.ui.component.CircledIconButton

@Composable
fun TodoField(selectedDate: LocalDate) {
    val colors = LocalAppColors.current
    val todoState = LocalTodoState.current

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        var text by remember { mutableStateOf("") }
        val hintVisible by remember { derivedStateOf { text.isEmpty() } }

        Box(
            modifier =
                Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(colors.bg(AppColor.SurfaceDim)),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (hintVisible) {
                Text(
                    text = "할 일을 입력하세요",
                    color = colors.fg(AppColor.SurfaceDim),
                    modifier =
                        Modifier
                            .alpha(0.5f)
                            .padding(start = 16.dp),
                )
            }
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle =
                    TextStyle(
                        color = colors.fg(AppColor.SurfaceDim),
                        fontSize = 16.sp,
                    ),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                singleLine = true,
                cursorBrush = SolidColor(colors.fg(AppColor.SurfaceDim)),
            )
        }

        Spacer(Modifier.width(8.dp))
        CircledIconButton(
            icon = Icons.Rounded.Add,
            onClick = {
                if (text.isNotBlank()) {
                    todoState.addTask(selectedDate, Task(text = text))
                    text = ""
                }
            },
        )
    }
}
