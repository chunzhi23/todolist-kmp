package org.kharis.todolist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import org.kharis.todolist.ui.component.CircledIconButton

@Composable
expect fun PlatformDatePickerDialog(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit,
)

@Composable
fun DatePickerRow(
    selectedDate: LocalDate,
    onDateChange: (LocalDate) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircledIconButton(
            icon = Icons.Rounded.ChevronLeft,
            onClick = {
                onDateChange(selectedDate.minus(DatePeriod(days = 1)))
            },
        )

        Spacer(modifier = Modifier.width(16.dp))

        DatePickerButton(
            selectedDate = selectedDate,
            onDateChange = onDateChange,
        )

        Spacer(modifier = Modifier.width(16.dp))

        CircledIconButton(
            icon = Icons.Rounded.ChevronRight,
            onClick = {
                onDateChange(selectedDate.plus(DatePeriod(days = 1)))
            },
        )
    }
}

@Composable
fun DatePickerButton(
    selectedDate: LocalDate,
    onDateChange: (LocalDate) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.height(40.dp),
        onClick = { openDialog = true },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.CalendarToday, contentDescription = "Calendar")
            Spacer(modifier = Modifier.width(8.dp))
            Text("$selectedDate")
        }
    }

    if (openDialog) {
        PlatformDatePickerDialog(
            selectedDate = selectedDate,
            onDateSelected = {
                openDialog = false
                onDateChange(it)
            },
            onDismissRequest = {
                openDialog = false
            },
        )
    }
}
