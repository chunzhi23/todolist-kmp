package org.kharis.todolist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform