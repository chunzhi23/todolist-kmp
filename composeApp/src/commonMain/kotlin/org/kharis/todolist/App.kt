package org.kharis.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kharis.todolist.state.LocalTodoState
import org.kharis.todolist.state.TodoState
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.CustomMaterialTheme
import org.kharis.todolist.theme.LocalAppColors
import org.kharis.todolist.ui.DatePickerRow
import org.kharis.todolist.ui.SpacingColumnCard
import org.kharis.todolist.ui.TodoField
import org.kharis.todolist.ui.TodoListView
import org.kharis.todolist.ui.Toolbar

@Composable
@Preview
fun App() {
    CustomMaterialTheme {
        val colors = LocalAppColors.current
        val todoState = remember { TodoState() }
        var selectedDate by remember {
            mutableStateOf(
                Clock.System
                    .now()
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                    .date,
            )
        }

        CompositionLocalProvider(LocalTodoState provides todoState) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(colors.bg(AppColor.Background))
                        .padding(WindowInsets.systemBars.asPaddingValues()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                /** TITLE */
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "TODO LIST",
                    fontWeight = FontWeight.W900,
                    fontSize = 28.sp,
                    color = colors.fg(AppColor.Background),
                )
                Spacer(Modifier.height(32.dp))

                /** ADDING TASK CARD */
                SpacingColumnCard {
                    DatePickerRow(
                        selectedDate = selectedDate,
                        onDateChange = { selectedDate = it },
                    )
                    TodoField(selectedDate)
                    Toolbar(selectedDate)
                }
                Spacer(Modifier.height(16.dp))

                /** TASK LISTVIEW */
                TodoListView(selectedDate)
            }
        }
    }
}
