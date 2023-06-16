package com.example.todolistapp_kotlin

interface TaskItemClickListener {
    fun editTaskItem(taskItem: TaskItem)
    fun  completeTaskItem(taskItem: TaskItem)
}