package org.kharis.todolist.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String = generateRandomId(),
    val text: String,
) {
    companion object {
        fun generateRandomId(): String {
            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            return (1..16).map { chars.random() }.joinToString("")
        }
    }
}
