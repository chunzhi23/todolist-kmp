package org.kharis.todolist.model

import kotlinx.datetime.LocalDate

sealed class Action {
    data class Add(
        val date: LocalDate,
        val task: Task,
    ) : Action()

    data class Delete(
        val date: LocalDate,
        val task: Task,
    ) : Action()

    data class DeleteAll(
        val date: LocalDate,
        val tasks: List<Task>,
    ) : Action()
}
