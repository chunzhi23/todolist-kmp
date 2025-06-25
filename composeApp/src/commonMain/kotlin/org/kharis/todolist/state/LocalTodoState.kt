package org.kharis.todolist.state

import androidx.compose.runtime.compositionLocalOf

val LocalTodoState =
    compositionLocalOf<TodoState> {
        error("TodoState not provided")
    }
