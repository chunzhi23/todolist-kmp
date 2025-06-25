package org.kharis.todolist.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.datetime.LocalDate
import org.kharis.todolist.model.Action
import org.kharis.todolist.model.Task

class TodoState {
    var taskMap by mutableStateOf(TodoStatePersistence.load())
        private set

    private val undoStack = mutableListOf<Action>()
    private val redoStack = mutableListOf<Action>()

    private fun updateAndSave(updated: Map<LocalDate, List<Task>>) {
        taskMap = updated
        TodoStatePersistence.save(taskMap)
    }

    fun addTask(
        date: LocalDate,
        task: Task,
    ) {
        val updated = taskMap.toMutableMap()
        val list = updated[date]?.toMutableList() ?: mutableListOf()
        list.add(task)
        updated[date] = list
        updateAndSave(updated)
        undoStack.add(Action.Add(date, task))
        redoStack.clear()
    }

    fun deleteTask(
        date: LocalDate,
        task: Task,
    ) {
        val updated = taskMap.toMutableMap()
        val list = updated[date].orEmpty()
        updated[date] = list.filterNot { it.id == task.id }
        updateAndSave(updated)
        undoStack.add(Action.Delete(date, task))
        redoStack.clear()
    }

    fun deleteAll(date: LocalDate) {
        val tasks = taskMap[date].orEmpty()
        if (tasks.isNotEmpty()) {
            val updated = taskMap.toMutableMap()
            updated[date] = emptyList()
            updateAndSave(updated)
            undoStack.add(Action.DeleteAll(date, tasks))
            redoStack.clear()
        }
    }

    fun undo() {
        if (undoStack.isEmpty()) return
        val action = undoStack.removeLast()
        when (action) {
            is Action.Add -> internalDelete(action.date, action.task)
            is Action.Delete -> internalAdd(action.date, action.task)
            is Action.DeleteAll -> internalRestoreAll(action.date, action.tasks)
        }
        redoStack.add(action)
    }

    fun redo() {
        if (redoStack.isEmpty()) return
        val action = redoStack.removeLast()
        when (action) {
            is Action.Add -> internalAdd(action.date, action.task)
            is Action.Delete -> internalDelete(action.date, action.task)
            is Action.DeleteAll -> internalDeleteAll(action.date)
        }
        undoStack.add(action)
    }

    private fun internalAdd(
        date: LocalDate,
        task: Task,
    ) {
        val updated = taskMap.toMutableMap()
        val list = updated[date]?.toMutableList() ?: mutableListOf()
        list.add(task)
        updated[date] = list
        updateAndSave(updated)
    }

    private fun internalDelete(
        date: LocalDate,
        task: Task,
    ) {
        val updated = taskMap.toMutableMap()
        val list = updated[date].orEmpty()
        updated[date] = list.filterNot { it.id == task.id }
        updateAndSave(updated)
    }

    private fun internalDeleteAll(date: LocalDate) {
        val updated = taskMap.toMutableMap()
        updated[date] = emptyList()
        updateAndSave(updated)
    }

    private fun internalRestoreAll(
        date: LocalDate,
        tasks: List<Task>,
    ) {
        val updated = taskMap.toMutableMap()
        updated[date] = tasks
        updateAndSave(updated)
    }
}
